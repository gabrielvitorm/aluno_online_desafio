package br.com.alunoonline.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DisciplinaRequestDTO(

        @NotBlank(message = "Nome da disciplina é obrigatório")
        String nome,

        @NotBlank(message = "Descrição é obirgatório")
        String descricao,

        @NotNull(message = "Carga horária é obrigatório")
        Integer cargaHoraria,

        @NotNull(message = "Professor Id é obrigatório")
        Long professorId
) {
}
