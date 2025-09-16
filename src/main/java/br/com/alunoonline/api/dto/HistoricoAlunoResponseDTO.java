package br.com.alunoonline.api.dto;

import br.com.alunoonline.api.enums.MatriculaCursoStatusEnum;

public record HistoricoAlunoResponseDTO(
        Long alunoId,
        String alunoNome,
        Long disciplinaId,
        String disciplinaNome,
        Double nota1,
        Double nota2,
        Double media,
        MatriculaCursoStatusEnum status
) {
}
