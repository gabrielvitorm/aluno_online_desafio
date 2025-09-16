package br.com.alunoonline.api.dto;

import jakarta.validation.constraints.NotNull;

public record MatriculaCursoRequestDTO(
        @NotNull(message = "Id do aluno é obrigatório")
        Long alunoId,
        @NotNull(message = "Id do curso é obrigatório")
        Long cursoId
) {
}
