package br.com.alunoonline.api.dto;

import jakarta.validation.constraints.NotBlank;

public record EnderecoDTO(
        @NotBlank(message = "CEP é obrigatório")
        String cep,

        @NotBlank(message = "logradouro é obrigatório")
        String logradouro,

        @NotBlank(message = "Número é obirgatório")
        String numero,

        @NotBlank(message = "complemento")
        String complemento,

        @NotBlank(message = "Bairro é obrigatório")
        String bairro,

        @NotBlank(message = "Cidade é obrigatório")
        String cidade,

        @NotBlank(message = "Estado é obrigatório")
        String estado,

        @NotBlank(message = "País é obrigatório")
        String pais
) {
}
