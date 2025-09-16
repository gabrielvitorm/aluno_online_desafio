package br.com.alunoonline.api.dto;

import br.com.alunoonline.api.enums.GeneroEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record ProfessorRequestDTO(

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @Email(message = "Email inválido")
        String email,

        @NotBlank(message = "RG é obrigatório")
        String rg,

        @CPF(message = "CPF inválido")
        String cpf,

        @NotBlank(message = "Telefone é obrigatório")
        String telefone,

        @NotNull(message = "Idade é obriagatório")
        Integer idade,

        @NotBlank(message = "Gênero é obrigatório")
        GeneroEnum genero,

        @Valid
        EnderecoDTO endereco
) {
}
