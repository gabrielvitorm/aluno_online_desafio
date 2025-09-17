package br.com.alunoonline.api.dto;

import br.com.alunoonline.api.enums.Role;

public record RegisterRequestDTO(
        String username,
        String password,
        String email,
        Role role
) {
}
