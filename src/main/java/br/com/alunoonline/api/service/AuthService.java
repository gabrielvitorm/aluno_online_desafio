package br.com.alunoonline.api.service;

import br.com.alunoonline.api.dto.AuthRequestDTO;
import br.com.alunoonline.api.dto.AuthResponseDTO;
import br.com.alunoonline.api.dto.RegisterRequestDTO;
import br.com.alunoonline.api.dto.UsuarioResponseDTO;
import br.com.alunoonline.api.model.Usuario;
import br.com.alunoonline.api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDTO register(RegisterRequestDTO request) {
        Usuario usuario = Usuario.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(request.role())
                .build();

        usuarioRepository.save(usuario);

        UserDetails userDetails = User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(usuario.getRole().name())
                .build();

        String token = jwtService.generateToken(userDetails);

        return new AuthResponseDTO(token);
    }

    public AuthResponseDTO login(AuthRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        Usuario usuario = usuarioRepository.findByUsername(request.username())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        UserDetails userDetails = User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(usuario.getRole().name())
                .build();

        String token = jwtService.generateToken(userDetails);

        return new AuthResponseDTO(token);
    }

    public UsuarioResponseDTO getUsuarioLogado(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return new UsuarioResponseDTO(
                usuario.getUsername(),
                usuario.getEmail(),
                usuario.getRole()
        );
    }
}
