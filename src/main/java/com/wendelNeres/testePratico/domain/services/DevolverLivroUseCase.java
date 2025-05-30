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

    private List<LivroDTO> listarLivros() {
        return biblioteca.getLivros().stream()
                .map(LivroDTO::new)
                .collect(Collectors.toList());
    }


    public ResponseDevolucaoDTO devolverLivro(String tituloLivro, UsuarioDTO usuarioDTO){

        List<LivroDTO> listaAntesOperacao = listarLivros();


        Livro livro = biblioteca.getLivros().stream()
                .filter(l -> l.getTitulo().equalsIgnoreCase(tituloLivro))
                .findFirst()
                .orElse(null);


        if(livro!= null ){
            if (!livro.isDisponivel()) {
                if (livro.getEmprestadoPara() != null) {
                    if (livro.getEmprestadoPara().getNome().equalsIgnoreCase(usuarioDTO.nome())) {

                        Usuario usuario = livro.getEmprestadoPara();

                        if (usuario instanceof Aluno aluno) {
                            aluno.setCreditos(aluno.getCreditos() + 1);
                        }

                        livro.setDisponivel(true);
                        livro.setEmprestadoPara(null);

                    } else throw new RuntimeException("Usuario diferente do que esta salvo na biblioteca");
                } else throw new RuntimeException("Nenhum usuario pegou este livro");
            }else throw new RuntimeException("Este livro ja está disponivel");
        }else throw new RuntimeException("Livro não encontrado");


        List<LivroDTO> listaDepoisOperacao = biblioteca.getLivros()
                .stream()
                .map(LivroDTO::new)
                .collect(Collectors.toList());


        return new ResponseDevolucaoDTO(listaAntesOperacao,listaDepoisOperacao);
    }


}
