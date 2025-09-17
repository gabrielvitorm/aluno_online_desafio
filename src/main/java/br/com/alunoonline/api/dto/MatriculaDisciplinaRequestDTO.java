package br.com.alunoonline.api.dto;

import br.com.alunoonline.api.enums.PeriodoLetivoEnum;
import jakarta.validation.constraints.NotNull;

public record MatriculaDisciplinaRequestDTO(
        @NotNull(message = "Id do aluno é obrigatório")
        Long alunoId,

        @NotNull(message = "Id da disciplina é obrigatório")
        Long disciplinaId,

        @NotNull(message = "Id do curso é obrigatório")
        Long matriculaCursoId,

        @NotNull(message = "Ano letivo é obrigatório")
        Integer anoLetivo,

        @NotNull(message = "Período é obrigatório")
        PeriodoLetivoEnum periodoLetivo
) {
}
