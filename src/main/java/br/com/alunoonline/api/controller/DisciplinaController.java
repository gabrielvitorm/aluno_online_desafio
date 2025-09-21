package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.dto.DisciplinaRequestDTO;
import br.com.alunoonline.api.dto.DisciplinaResponseDTO;
import br.com.alunoonline.api.service.DisciplinaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplinas")
@RequiredArgsConstructor
@Tag(name = "Disciplinas", description = "Gerenciamento de disciplinas")
@SecurityRequirement(name = "bearerAuth")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    @PreAuthorize("hasAnyRole('PROFESSOR','COORDENADOR')")
    @Operation(
            summary = "Cadastrar Disciplina",
            description = "Cria uma nova disciplina. **Permissão**: PROFESSOR ou COORDENADOR."
    )
    @PostMapping
    public ResponseEntity<DisciplinaResponseDTO> criarDisciplina(@Valid @RequestBody DisciplinaRequestDTO dto) {
        DisciplinaResponseDTO disciplinaCriada = disciplinaService.criarDisciplina(dto);
        return ResponseEntity.status(201).body(disciplinaCriada);
    }

    @PreAuthorize("hasRole('COORDENADOR') or (hasRole('PROFESSOR') and @ownershipEvaluator.isProfessorDaDisciplina(#id, authentication))")
    @Operation(
            summary = "Listar Disciplinas",
            description = "Lista todas as disciplinas ativas (não excluídas). **Permissão**: autenticado."
    )
    @GetMapping
    public ResponseEntity<List<DisciplinaResponseDTO>> listarDisciplinas() {
        return ResponseEntity.ok(disciplinaService.listarDisciplinas());
    }

    @PreAuthorize("hasRole('COORDENADOR') or (hasRole('PROFESSOR') and @ownershipEvaluator.isProfessorDaDisciplina(#id, authentication))")
    @Operation(
            summary = "Obter Disciplina por ID",
            description = "Retorna a disciplina pelo ID (não excluída). **Permissão**: autenticado."
    )
    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaResponseDTO> listarDisciplinaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(disciplinaService.listarDisciplinaPorId(id));
    }

    @PreAuthorize("hasRole('COORDENADOR') or (hasRole('PROFESSOR') and @ownershipEvaluator.isProfessorDaDisciplina(#id, authentication))")
    @Operation(
            summary = "Atualizar Disciplina",
            description = "Atualiza uma disciplina pelo ID. **Permissão**: COORDENADOR ou PROFESSOR responsável pela disciplina."
    )
    @PutMapping("/editar-disciplina/{id}")
    public ResponseEntity<DisciplinaResponseDTO> atualizarDisciplina(
            @PathVariable Long id,
            @Valid @RequestBody DisciplinaRequestDTO dto
    ) {
        DisciplinaResponseDTO disciplinaEditada = disciplinaService.atualizarDisciplina(id, dto);
        return ResponseEntity.ok(disciplinaEditada);
    }

    @PreAuthorize("hasRole('COORDENADOR')")
    @Operation(
            summary = "Excluir Disciplina (soft delete)",
            description = "Marca a disciplina como excluída (`excluido = true`). **Permissão**: COORDENADOR. Retorna 204."
    )
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> deletarDisciplina(@PathVariable Long id) {
        disciplinaService.deletarDisciplina(id);
        return ResponseEntity.noContent().build();
    }
}
