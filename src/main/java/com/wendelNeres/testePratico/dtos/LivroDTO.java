package com.wendelNeres.testePratico.dtos;

import com.wendelNeres.testePratico.domain.entities.Livro;
import com.wendelNeres.testePratico.domain.entities.Usuario;

public record LivroDTO( String titulo, int valorCredito, boolean disponivel, String emprestadoPara) {

    public LivroDTO(Livro livro){
        this(
                livro.getTitulo(),
                livro.getValorCredito(),
                livro.isDisponivel(),
                livro.getEmprestadoPara()!= null ? livro.getEmprestadoPara().getNome(): null
        );

    }
}
