package com.wendelNeres.testePratico.mappers;

import com.wendelNeres.testePratico.domain.entities.Aluno;
import com.wendelNeres.testePratico.domain.entities.Professor;
import com.wendelNeres.testePratico.domain.entities.Usuario;
import com.wendelNeres.testePratico.dtos.UsuarioDTO;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioDTO dto) {

        if (dto == null) {
            throw new IllegalArgumentException("Usuário DTO não pode ser nulo");
        }

        if ("ALUNO".equalsIgnoreCase(dto.tipoUsuario())) {
            return new Aluno(dto.nome()); // créditos padrão definidos internamente
        } else if ("PROFESSOR".equalsIgnoreCase(dto.tipoUsuario())) {
            return new Professor(dto.nome());
        }
        throw new IllegalArgumentException("Tipo de usuário inválido");
    }
}
