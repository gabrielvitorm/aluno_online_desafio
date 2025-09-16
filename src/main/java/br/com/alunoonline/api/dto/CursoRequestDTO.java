package br.com.alunoonline.api.dto;

import br.com.alunoonline.api.enums.TipoCursoEnum;
import jakarta.validation.constraints.NotBlank;

public record CursoRequestDTO(

        @NotBlank(message = "Nome do curso é obrigatório")
        String nome,

        @NotBlank(message = "Tipo do curso é obrigatório")
        TipoCursoEnum tipoCurso
) {
}
