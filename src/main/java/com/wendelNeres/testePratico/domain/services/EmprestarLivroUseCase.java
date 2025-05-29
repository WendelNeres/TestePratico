package com.wendelNeres.testePratico.domain.services;

import com.wendelNeres.testePratico.domain.entities.Aluno;
import com.wendelNeres.testePratico.domain.entities.Biblioteca;
import com.wendelNeres.testePratico.domain.entities.Livro;
import com.wendelNeres.testePratico.domain.entities.Usuario;

import com.wendelNeres.testePratico.dtos.UsuarioDTO;
import com.wendelNeres.testePratico.mappers.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class EmprestarLivroUseCase {

    @Autowired
    private Biblioteca biblioteca;



    public boolean emprestarLivro(String tituloLivro, UsuarioDTO usuarioDTO){

        Usuario usuario = UsuarioMapper.toEntity(usuarioDTO);
            Livro livro = biblioteca.getLivros()
                    .stream()
                    .filter(l -> l.getTitulo().equalsIgnoreCase(tituloLivro) && l.isDisponivel())
                    .findFirst()
                    .orElse(null);
            if (livro == null){
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

}
