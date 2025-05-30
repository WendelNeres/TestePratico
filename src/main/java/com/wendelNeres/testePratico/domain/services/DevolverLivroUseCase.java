package com.wendelNeres.testePratico.domain.services;

import com.wendelNeres.testePratico.domain.entities.Aluno;
import com.wendelNeres.testePratico.domain.entities.Biblioteca;
import com.wendelNeres.testePratico.domain.entities.Livro;
import com.wendelNeres.testePratico.domain.entities.Usuario;
import com.wendelNeres.testePratico.dtos.ResponseDevolucaoDTO;
import com.wendelNeres.testePratico.dtos.LivroDTO;

import com.wendelNeres.testePratico.dtos.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DevolverLivroUseCase {

    @Autowired
    private Biblioteca biblioteca;

    private List<LivroDTO> listarLivrosDisponiveis() {
        return biblioteca.getLivros().stream()
                .filter(Livro::isDisponivel)
                .map(LivroDTO::new)
                .collect(Collectors.toList());
    }


    public ResponseDevolucaoDTO devolverLivro(String tituloLivro, UsuarioDTO usuarioDTO){

        List<LivroDTO> listaAntesOperacao = listarLivrosDisponiveis();


        Livro livro = biblioteca.getLivros().stream()
                .filter(l -> l.getTitulo().equalsIgnoreCase(tituloLivro))
                .findFirst()
                .orElse(null);



        if(livro!= null && !livro.isDisponivel() && livro.getEmprestadoPara() !=null &&
                livro.getEmprestadoPara().getNome().equalsIgnoreCase(usuarioDTO.nome())){

            Usuario usuario = livro.getEmprestadoPara();

            if (usuario instanceof Aluno aluno){
                aluno.setCreditos( aluno.getCreditos()+1 );
            }
            livro.setDisponivel(true);
            livro.setEmprestadoPara(null);

        }


        List<LivroDTO> listaDepoisOperacao = biblioteca.getLivros()
                .stream()
                .map(LivroDTO::new)
                .collect(Collectors.toList());


        return new ResponseDevolucaoDTO(listaAntesOperacao,listaDepoisOperacao);
    }


}
