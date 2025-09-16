package br.com.alunoonline.api.dto;

import br.com.alunoonline.api.enums.TipoCursoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CursoRequestDTO(

        @NotBlank(message = "Nome do curso é obrigatório")
        String nome,

        @NotNull(message = "Tipo do curso é obrigatório")
        TipoCursoEnum tipoCurso
) {
}
