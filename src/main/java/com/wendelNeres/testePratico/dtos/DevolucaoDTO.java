package com.wendelNeres.testePratico.dtos;

import java.util.List;

public record DevolucaoDTO(
        List<LivroDTO> antesOperacao,
        List<LivroDTO> depoisOperacao
) {
}
