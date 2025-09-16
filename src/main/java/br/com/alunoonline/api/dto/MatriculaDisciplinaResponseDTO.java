package br.com.alunoonline.api.dto;

import br.com.alunoonline.api.enums.SituacaoStatusEnum;

public record MatriculaDisciplinaResponseDTO(
        Long id,
        Long alunoId,
        String alunoNome,
        Long disciplinaId,
        String disciplinaNome,
        Long cursoId,
        String cursoNome,
        Integer anoLetivo,
        Integer periodo,
        SituacaoStatusEnum status,
        Double nota1,
        Double nota2,
        Double media
) {
}
