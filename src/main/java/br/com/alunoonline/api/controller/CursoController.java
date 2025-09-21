package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.dto.CursoRequestDTO;
import br.com.alunoonline.api.dto.CursoResponseDTO;
import br.com.alunoonline.api.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
@Tag(name = "Cursos", description = "Gerenciamento de cursos")
@SecurityRequirement(name = "bearerAuth")
public class CursoController {

    private final CursoService cursoService;

    @PreAuthorize("hasRole('COORDENADOR')")
    @Operation(
            summary = "Cadastrar Curso",
            description = "Cria um novo curso. **Permissão**: COORDENADOR."
    )
    @PostMapping
    public ResponseEntity<CursoResponseDTO> criarCurso(@Valid @RequestBody CursoRequestDTO dto) {
        CursoResponseDTO cursoCriado = cursoService.criarCurso(dto);
        return ResponseEntity.status(201).body(cursoCriado);
    }

    @PreAuthorize("hasRole('COORDENADOR')")
    @Operation(
            summary = "Listar Cursos",
            description = "Lista todos os cursos (apenas registros não excluídos). **Permissão**: autenticado."
    )
    @GetMapping
    public ResponseEntity<List<CursoResponseDTO>> listarCursos() {
        return ResponseEntity.ok(cursoService.listarCursos());
    }

    @PreAuthorize("hasRole('COORDENADOR')")
    @Operation(
            summary = "Obter Curso por ID",
            description = "Retorna um curso pelo ID (não excluído). **Permissão**: autenticado."
    )
    @GetMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> listarCursoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cursoService.listarCursoPorId(id));
    }

    @PreAuthorize("hasRole('COORDENADOR')")
    @Operation(
            summary = "Atualizar Curso",
            description = "Atualiza um curso pelo ID. **Permissão**: COORDENADOR."
    )
    @PutMapping("/editar-curso/{id}")
    public ResponseEntity<CursoResponseDTO> atualizarCurso(
            @PathVariable Long id,
            @Valid @RequestBody CursoRequestDTO dto
    ) {
        CursoResponseDTO cursoEditado = cursoService.atualizarCurso(id, dto);
        return ResponseEntity.ok(cursoEditado);
    }

    @PreAuthorize("hasRole('COORDENADOR')")
    @Operation(
            summary = "Excluir Curso (soft delete)",
            description = "Marca o curso como excluído (`excluido = true`). **Permissão**: COORDENADOR. Retorna 204."
    )
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirCurso(@PathVariable Long id) {
        cursoService.deletarCurso(id); // soft delete no service
        return ResponseEntity.noContent().build();
    }
}
