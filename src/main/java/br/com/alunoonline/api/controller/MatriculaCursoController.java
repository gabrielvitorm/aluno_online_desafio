package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.dto.MatriculaCursoRequestDTO;
import br.com.alunoonline.api.dto.MatriculaCursoResponseDTO;
import br.com.alunoonline.api.service.MatriculaCursoService;
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
@RequestMapping("/matricula-curso")
@RequiredArgsConstructor
@Tag(name = "Matrícula em Curso", description = "Gerenciamento de matrículas de alunos em cursos")
@SecurityRequirement(name = "bearerAuth")
public class MatriculaCursoController {

    private final MatriculaCursoService matriculaCursoService;

    @PreAuthorize("hasRole('COORDENADOR') or (hasRole('ALUNO') and @ownershipEvaluator.isAlunoDoUsuario(#dto.alunoId(), authentication))")
    @Operation(
            summary = "Criar matrícula em curso",
            description = "Cria uma matrícula de um aluno em um curso. **Permissão**: COORDENADOR ou ALUNO (apenas para si).",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Criado",
                            content = @Content(schema = @Schema(implementation = MatriculaCursoResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "401", description = "Não autenticado"),
                    @ApiResponse(responseCode = "403", description = "Sem permissão")
            }
    )
    @PostMapping
    public ResponseEntity<MatriculaCursoResponseDTO> criarMatricula(@Valid @RequestBody MatriculaCursoRequestDTO dto) {
        MatriculaCursoResponseDTO matriculaAdicionada = matriculaCursoService.criarMatricula(dto);
        return ResponseEntity.status(201).body(matriculaAdicionada);
    }

    @PreAuthorize("hasRole('COORDENADOR')")
    @Operation(
            summary = "Listar matrículas",
            description = "Lista todas as matrículas ativas (não excluídas). **Permissão**: COORDENADOR.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "401", description = "Não autenticado"),
                    @ApiResponse(responseCode = "403", description = "Sem permissão")
            }
    )
    @GetMapping
    public ResponseEntity<List<MatriculaCursoResponseDTO>> listarMatriculas() {
        return ResponseEntity.ok(matriculaCursoService.listarMatriculas());
    }

    @PreAuthorize("hasRole('COORDENADOR') or (hasRole('ALUNO') and @ownershipEvaluator.isMatriculaCursoDoUsuario(#id, authentication))")
    @Operation(
            summary = "Obter matrícula por ID",
            description = "Retorna a matrícula pelo ID. **Permissão**: autenticado.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(schema = @Schema(implementation = MatriculaCursoResponseDTO.class))),
                    @ApiResponse(responseCode = "401", description = "Não autenticado"),
                    @ApiResponse(responseCode = "404", description = "Não encontrado")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<MatriculaCursoResponseDTO> listarMatriculaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(matriculaCursoService.listarPorId(id));
    }

    @PreAuthorize("hasRole('COORDENADOR')")
    @Operation(
            summary = "Trancar matrícula",
            description = "Tranca a matrícula do aluno. **Permissão**: COORDENADOR.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Atualizado",
                            content = @Content(schema = @Schema(implementation = MatriculaCursoResponseDTO.class))),
                    @ApiResponse(responseCode = "401", description = "Não autenticado"),
                    @ApiResponse(responseCode = "403", description = "Sem permissão"),
                    @ApiResponse(responseCode = "404", description = "Não encontrado")
            }
    )
    @PatchMapping("/trancar/{id}")
    public ResponseEntity<MatriculaCursoResponseDTO> trancarMatricula(@PathVariable Long id) {
        return ResponseEntity.ok(matriculaCursoService.trancarMatricula(id));
    }

    @PreAuthorize("hasRole('COORDENADOR')")
    @Operation(
            summary = "Reativar matrícula",
            description = "Reativa a matrícula trancada. **Permissão**: COORDENADOR."
    )
    @PatchMapping("/reativar/{id}")
    public ResponseEntity<MatriculaCursoResponseDTO> reativarMatricula(@PathVariable Long id) {
        return ResponseEntity.ok(matriculaCursoService.reativarMatricula(id));
    }

    @PreAuthorize("hasRole('COORDENADOR')")
    @Operation(
            summary = "Concluir matrícula",
            description = "Conclui a matrícula do aluno. **Permissão**: COORDENADOR."
    )
    @PatchMapping("/concluir/{id}")
    public ResponseEntity<MatriculaCursoResponseDTO> concluirMatricula(@PathVariable Long id) {
        return ResponseEntity.ok(matriculaCursoService.concluirMatricula(id));
    }

    @PreAuthorize("hasRole('COORDENADOR')")
    @Operation(
            summary = "Atualizar matrícula",
            description = "Atualiza dados da matrícula pelo ID. **Permissão**: COORDENADOR."
    )
    @PutMapping("/{id}")
    public ResponseEntity<MatriculaCursoResponseDTO> atualizarMatricula(
            @PathVariable Long id,
            @Valid @RequestBody MatriculaCursoRequestDTO dto
    ) {
        MatriculaCursoResponseDTO matriculaEditada = matriculaCursoService.atualizarMatricula(id, dto);
        return ResponseEntity.ok(matriculaEditada);
    }

    @PreAuthorize("hasRole('COORDENADOR')")
    @Operation(
            summary = "Excluir matrícula (soft delete)",
            description = "Marca a matrícula como excluída (`excluido = true`). **Permissão**: COORDENADOR. Retorna 204."
    )
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarMatricula(@PathVariable Long id) {
        matriculaCursoService.deletarMatricula(id); // soft delete no service
        return ResponseEntity.noContent().build();
    }
}
