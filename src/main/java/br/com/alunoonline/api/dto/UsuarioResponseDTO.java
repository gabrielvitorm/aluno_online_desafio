package br.com.alunoonline.api.dto;

import br.com.alunoonline.api.enums.Role;

public record UsuarioResponseDTO(
        String username,
        String email,
        Role role
) {
}
