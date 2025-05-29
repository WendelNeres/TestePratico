package com.wendelNeres.testePratico.domain.entities;


import com.wendelNeres.testePratico.dtos.LivroDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class Biblioteca {
    private List<Livro> livros = new ArrayList<>();

    public Biblioteca(){
    }

    public List<Livro> getLivros(){
        return livros;
    }
}
