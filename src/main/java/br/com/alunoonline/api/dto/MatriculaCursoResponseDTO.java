package br.com.alunoonline.api.dto;

import br.com.alunoonline.api.enums.MatriculaCursoStatusEnum;

import java.time.LocalDate;

public record MatriculaCursoResponseDTO(
        Long id,
        Long alunoId,
        String alunoNome,
        Long cursoId,
        String cursoNome,
        MatriculaCursoStatusEnum status,
        LocalDate dataMatricula,
        LocalDate dataTrancamento,
        LocalDate dataConclusao
) {
}
