package br.com.alunoonline.api.dto;

import br.com.alunoonline.api.enums.GeneroEnum;

public record AlunoResponseDTO(
        Long id,
        String nome,
        String email,
        String rg,
        String cpf,
        String telefone,
        Integer idade,
        GeneroEnum genero,
        Long cursoId,
        String cursoNome,
        EnderecoDTO endereco
) {
}
