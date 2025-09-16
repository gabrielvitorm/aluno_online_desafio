package br.com.alunoonline.api.dto;

public record DisciplinaResponseDTO(
        Long id,
        String nome,
        String descriao,
        Integer cargarHorario,
        Boolean excluido,
        Long professorId,
        String professorNome
) {
}
