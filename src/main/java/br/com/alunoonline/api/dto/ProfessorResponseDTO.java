package br.com.alunoonline.api.dto;

import br.com.alunoonline.api.enums.GeneroEnum;

public record ProfessorResponseDTO(
        Long id,
        String nome,
        String email,
        String rg,
        String cpf,
        String telefone,
        Integer idade,
        GeneroEnum genero,
        EnderecoDTO endereco
) {
}
