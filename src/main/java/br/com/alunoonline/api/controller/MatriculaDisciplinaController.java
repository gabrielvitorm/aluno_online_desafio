package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.dto.AtualizarNotasRequestDTO;
import br.com.alunoonline.api.dto.MatriculaDisciplinaRequestDTO;
import br.com.alunoonline.api.dto.MatriculaDisciplinaResponseDTO;
import br.com.alunoonline.api.service.MatriculaDisciplinaService;
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
@RequestMapping("/matricula-disciplina")
@RequiredArgsConstructor
@Tag(name = "Matrícula em Disciplina", description = "Gerenciamento de matrículas de alunos em disciplinas")
@SecurityRequirement(name = "bearerAuth")
public class MatriculaDisciplinaController {

    private final MatriculaDisciplinaService matriculaDisciplinaService;

    @PreAuthorize("hasRole('COORDENADOR') or (hasRole('ALUNO') and @ownershipEvaluator.isAlunoDoUsuario(#dto.alunoId(), authentication))")
    @Operation(
            summary = "Criar matrícula em disciplina",
            description = "Cria a matrícula de um aluno em uma disciplina. **Permissão**: COORDENADOR ou ALUNO (apenas para si)."
    )
    @PostMapping
    public ResponseEntity<MatriculaDisciplinaResponseDTO> criarMatricula(
            @Valid @RequestBody MatriculaDisciplinaRequestDTO dto
    ) {
        MatriculaDisciplinaResponseDTO matriculaCriada = matriculaDisciplinaService.criarMatricula(dto);
        return ResponseEntity.status(201).body(matriculaCriada);
    }

    @PreAuthorize("hasRole('COORDENADOR')")
    @Operation(
            summary = "Listar Todas as matrículas em disciplina",
            description = "Lista todas as matrículas de todos alunos em uma disciplina. **Permissão**: COORDENADOR."
    )
    @GetMapping
    public ResponseEntity<List<MatriculaDisciplinaResponseDTO>> listarMatriculas() {
        return ResponseEntity.ok(matriculaDisciplinaService.listarMatriculas());
    }

    @PreAuthorize("hasRole('COORDENADOR')")
    @Operation(
            summary = "Listar matrícula em disciplina por ID",
            description = "Lista a matrícula de um aluno em uma disciplina por ID. **Permissão**: COORDENADOR."
    )
    @GetMapping("/{id}")
    public ResponseEntity<MatriculaDisciplinaResponseDTO> listarMatriculaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(matriculaDisciplinaService.listarPorId(id));
    }

    @PreAuthorize("hasRole('COORDENADOR') or hasRole('PROFESSOR')")
    @Operation(
            summary = "Atualiza notas em disciplina por ID",
            description = "Atualiza as notas de um aluno em uma disciplina por ID. **Permissão**: COORDENADOR ou PROFESSOR."
    )
    @PatchMapping("/atualizar-notas/{id}")
    public ResponseEntity<MatriculaDisciplinaResponseDTO> atualizarNotas(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarNotasRequestDTO dto
    ) {
        MatriculaDisciplinaResponseDTO notasAtualizadas = matriculaDisciplinaService.atualizarNotas(id, dto);

        return ResponseEntity.ok(notasAtualizadas);
    }

    @PreAuthorize("hasRole('COORDENADOR')")
    @Operation(
            summary = "Tranca uma disciplina por ID",
            description = "Tranca uma disciplina por ID. **Permissão**: COORDENADOR."
    )
    @PatchMapping("/trancar-matricula/{id}")
    public ResponseEntity<MatriculaDisciplinaResponseDTO> trancarMatricula(@PathVariable Long id) {
        return ResponseEntity.ok(matriculaDisciplinaService.trancarDisciplina(id));
    }

    @PreAuthorize("hasRole('COORDENADOR')")
    @Operation(
            summary = "Atualizar uma Disciplina",
            description = "Atualiza uma disciplina por ID. **Permissão**: COORDENADOR."
    )
    @PutMapping("/atualizar-matricula/{id}")
    public ResponseEntity<MatriculaDisciplinaResponseDTO> atualizarMatricula(
            @PathVariable Long id,
            @Valid @RequestBody MatriculaDisciplinaRequestDTO dto
    ) {
        MatriculaDisciplinaResponseDTO matriculaAtualizada = matriculaDisciplinaService.atualizarMatricula(id, dto);

        return ResponseEntity.ok(matriculaAtualizada);
    }
}
