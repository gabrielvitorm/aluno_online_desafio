package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.dto.AuthRequestDTO;
import br.com.alunoonline.api.dto.AuthResponseDTO;
import br.com.alunoonline.api.dto.RegisterRequestDTO;
import br.com.alunoonline.api.dto.UsuarioResponseDTO;
import br.com.alunoonline.api.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Login", description = "Gerenciamento de login")
public class AuthController {

    private final AuthService authService;

    @Operation(
            summary = "Registrar um Usuário",
            description = "Faz um registro de usuário no Banco de dados e define o papel do usuário"
    )
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @Operation(
            summary = "Login usuário",
            description = "Faz o login do usuário e atribui as permissões"
    )
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/me")
    public ResponseEntity<UsuarioResponseDTO> getMe() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).build();
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails userDetails) {
            return ResponseEntity.ok(authService.getUsuarioLogado(userDetails.getUsername()));
        }

        return ResponseEntity.status(401).build();
    }
}
