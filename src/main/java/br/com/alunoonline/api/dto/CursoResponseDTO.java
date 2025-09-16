package br.com.alunoonline.api.dto;

import br.com.alunoonline.api.enums.TipoCursoEnum;

public record CursoResponseDTO(
        Long id,
        String nome,
        TipoCursoEnum tipoCurso
) {
}
