package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.dto.ProfessorRequestDTO;
import br.com.alunoonline.api.dto.ProfessorResponseDTO;
import br.com.alunoonline.api.service.ProfessorService;
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
@RequestMapping("/professores")
@RequiredArgsConstructor
@Tag(name = "Professores", description = "Gerenciamento de professores")
@SecurityRequirement(name = "bearerAuth")
public class ProfessorController {

    private final ProfessorService professorService;

    @PreAuthorize("hasRole('COORDENADOR')")
    @Operation(
            summary = "Cadastrar Professor",
            description = "Cria um novo professor. **Permissão**: COORDENADOR."
    )
    @PostMapping
    public ResponseEntity<ProfessorResponseDTO> criarProfessor(@Valid @RequestBody ProfessorRequestDTO dto) {
        ProfessorResponseDTO professorCriado = professorService.criarProfessor(dto);
        return ResponseEntity.status(201).body(professorCriado);
    }

    @PreAuthorize("hasRole('COORDENADOR')")
    @Operation(
            summary = "Listar Professores",
            description = "Lista todos os professores ativos (não excluídos). **Permissão**: autenticado."
    )
    @GetMapping
    public ResponseEntity<List<ProfessorResponseDTO>> listarProfessores() {
        return ResponseEntity.ok(professorService.listarProfessores());
    }

    @PreAuthorize("hasRole('PROFESSOR')")
    @Operation(
            summary = "Obter Professor por ID",
            description = "Retorna um professor pelo ID (não excluído). **Permissão**: autenticado."
    )
    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponseDTO> listarProfessorPorId(@PathVariable Long id) {
        return ResponseEntity.ok(professorService.listarProfessorPorId(id));
    }

    @PreAuthorize("hasRoleAny('COORDENADOR', 'PROFESSOR')")
    @Operation(
            summary = "Atualizar Professor",
            description = "Atualiza dados de um professor pelo ID. **Permissão**: COORDENADOR."
    )
    @PutMapping("/editar-professor/{id}")
    public ResponseEntity<ProfessorResponseDTO> atualizarProfessor(
            @PathVariable Long id,
            @Valid @RequestBody ProfessorRequestDTO dto
    ) {
        ProfessorResponseDTO professorAtualizado = professorService.atualizarProfessor(id, dto);
        return ResponseEntity.ok(professorAtualizado);
    }

    @PreAuthorize("hasRole('COORDENADOR')")
    @Operation(
            summary = "Excluir Professor (soft delete)",
            description = "Marca o professor como excluído (`excluido = true`). **Permissão**: COORDENADOR. Retorna 204."
    )
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarProfessor(@PathVariable Long id) {
        professorService.deletarProfessor(id); // soft delete no service/entidade
        return ResponseEntity.noContent().build();
    }
}
