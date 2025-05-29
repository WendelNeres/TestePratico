package com.wendelNeres.testePratico.dtos;

import java.util.List;

public record ResponseDevolucaoDTO(
        List<LivroDTO> antesOperacao,
        List<LivroDTO> depoisOperacao
) {
}
