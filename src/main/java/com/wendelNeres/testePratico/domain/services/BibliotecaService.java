package com.wendelNeres.testePratico.domain.services;

import com.wendelNeres.testePratico.domain.entities.Aluno;
import com.wendelNeres.testePratico.domain.entities.Biblioteca;
import com.wendelNeres.testePratico.domain.entities.Livro;
import com.wendelNeres.testePratico.domain.entities.Usuario;
import com.wendelNeres.testePratico.dtos.DevolucaoDTO;
import com.wendelNeres.testePratico.dtos.LivroDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BibliotecaService {

    @Autowired
    private Biblioteca biblioteca;



    public boolean emprestarLivro(Livro livro, Usuario usuario){
        if (!livro.isDisponivel()){
            return false;

        }
        if (!usuario.emprestimo()){
            return false;
        }
        if (usuario instanceof Aluno aluno){
            int creditos = aluno.getCreditos();
            aluno.setCreditos(creditos - livro.getValorCredito());
        }
        livro.setDisponivel(false);
        livro.setUsuario(usuario);

        return true;
    }


    private List<LivroDTO> listarLivrosDisponiveis() {
        return biblioteca.getLivros().stream()
                .filter(Livro::isDisponivel)
                .map(LivroDTO::new)
                .collect(Collectors.toList());
    }


    public DevolucaoDTO devolverLivro(String tituloLivro, String nomeUsuario){

        List<LivroDTO> listaAntesOperacao = listarLivrosDisponiveis();

        Livro livro = biblioteca.getLivros().stream()
                .filter(l -> l.getTitulo().equalsIgnoreCase(tituloLivro))
                .findFirst()
                .orElse(null);

        if(livro!= null && !livro.isDisponivel() && livro.getUsuario() !=null &&
                livro.getUsuario().getNome().equalsIgnoreCase(nomeUsuario)){

            Usuario usuario = livro.getUsuario();

            if (usuario instanceof Aluno aluno){
                aluno.setCreditos( aluno.getCreditos()+1 );
            }

            livro.setDisponivel(true);
            livro.setUsuario(null);
        }


        List<LivroDTO> listaDepoisOperacao = biblioteca.getLivros()
                .stream()
                .map(LivroDTO::new)
                .collect(Collectors.toList());

        return new DevolucaoDTO(listaAntesOperacao,listaDepoisOperacao);
    }


}
