package br.com.alunoonline.api.dto;

public record AuthRequestDTO(
        String username,
        String password
) {
}
