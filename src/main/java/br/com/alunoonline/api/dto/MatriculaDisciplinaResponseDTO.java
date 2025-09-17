package br.com.alunoonline.api.dto;

import br.com.alunoonline.api.enums.PeriodoLetivoEnum;
import br.com.alunoonline.api.enums.SituacaoStatusEnum;

public record MatriculaDisciplinaResponseDTO(
        Long id,
        Long alunoId,
        String alunoNome,
        Long disciplinaId,
        String disciplinaNome,
        Long matriculaCursoId,
        String matriculaCursoNome,
        Integer anoLetivo,
        PeriodoLetivoEnum periodoLetivo,
        SituacaoStatusEnum status,
        Double nota1,
        Double nota2,
        Double media
) {
}
