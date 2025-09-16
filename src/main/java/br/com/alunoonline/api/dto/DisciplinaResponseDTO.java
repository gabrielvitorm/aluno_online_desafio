package br.com.alunoonline.api.dto;

public record DisciplinaResponseDTO(
        Long id,
        String nome,
        String descricao,
        Integer cargaHoraria,
        Boolean excluido,
        Long professorId,
        String professorNome,
        Long cursoId,
        String cursoNome
) {
}
