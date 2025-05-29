package com.wendelNeres.testePratico.domain.entities;


import com.wendelNeres.testePratico.dtos.LivroDTO;
import com.wendelNeres.testePratico.dtos.UsuarioDTO;
import com.wendelNeres.testePratico.mappers.LivroMapper;
import com.wendelNeres.testePratico.mappers.UsuarioMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class Biblioteca {
    private List<Livro> livros = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();


    public Biblioteca(){
    }

    public void adicionarLivro(LivroDTO livroDTO) {
        Livro livro = LivroMapper.toEntity(livroDTO);
        livros.add(livro);
    }

    public void adicionarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = UsuarioMapper.toEntity(usuarioDTO);
        usuarios.add(usuario);
    }

    public List<Livro> getLivros(){
        return livros;
    }
}
