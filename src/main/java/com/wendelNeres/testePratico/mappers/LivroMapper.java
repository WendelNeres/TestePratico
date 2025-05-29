package com.wendelNeres.testePratico.mappers;

import com.wendelNeres.testePratico.domain.entities.Livro;
import com.wendelNeres.testePratico.dtos.LivroDTO;

public class LivroMapper {
    public static Livro toEntity(LivroDTO dto) {
        return new Livro(dto); // Assumindo construtor apropriado
    }
}
