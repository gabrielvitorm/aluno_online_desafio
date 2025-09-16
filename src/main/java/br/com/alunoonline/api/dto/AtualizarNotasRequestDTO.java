package br.com.alunoonline.api.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

public record AtualizarNotasRequestDTO(
        @DecimalMin(value = "0.0", message = "Primeira nota deve ser maior ou igual a 0")
        @DecimalMax(value = "10.0", message = "nota1 deve ser menor ou igual a 10")
        Double nota1,

        @DecimalMin(value = "0.0", message = "Segunda nota deve ser maior ou igual a 0")
        @DecimalMax(value = "10.0", message = "Segunda nota deve ser menor ou igual a 10")
        Double nota2
) {
}
