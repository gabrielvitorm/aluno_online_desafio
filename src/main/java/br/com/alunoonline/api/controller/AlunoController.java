package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.dto.AlunoRequestDTO;
import br.com.alunoonline.api.dto.AlunoResponseDTO;
import br.com.alunoonline.api.service.AlunoService;
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
@RequestMapping("/alunos")
@RequiredArgsConstructor
@Tag(name = "Alunos", description = "Gerenciamento de alunos")
@SecurityRequirement(name = "bearerAuth")
public class AlunoController {

    private final AlunoService alunoService;

    @PreAuthorize("hasAnyRole('ALUNO','COORDENADOR')")
    @Operation(
            summary = "Cadastrar Aluno",
            description = "Cria um novo aluno no sistema. **Permissões**: ALUNO ou COORDENADOR."
    )
    @PostMapping
    public ResponseEntity<AlunoResponseDTO> criarAluno(@Valid @RequestBody AlunoRequestDTO dto) {
        AlunoResponseDTO alunoCriado = alunoService.criarAluno(dto);
        return ResponseEntity.status(201).body(alunoCriado);
    }

    @PreAuthorize("hasRole('COORDENADOR')")
    @Operation(
            summary = "Listar Alunos",
            description = "Lista todos os alunos no sistema. **Permissão**: COORDENADOR."
    )
    @GetMapping
    public ResponseEntity<List<AlunoResponseDTO>> listarAlunos() {
        return ResponseEntity.ok(alunoService.listarAlunos());
    }

    @PreAuthorize("hasAnyRole('ALUNO','COORDENADOR')")
    @Operation(
            summary = "Listar Aluno por Id",
            description = "Retorna o aluno a partir do ID."
    )
    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> listarAlunoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(alunoService.listarAlunoPorId(id));
    }

    @PreAuthorize("hasAnyRole('ALUNO','COORDENADOR')")
    @Operation(
            summary = "Atualizar Aluno",
            description = "Atualiza um aluno no sistema a partir do ID."
    )
    @PutMapping("/editar-aluno/{id}")
    public ResponseEntity<AlunoResponseDTO> atualizarAluno(
            @PathVariable Long id,
            @Valid @RequestBody AlunoRequestDTO dto
    ) {
        AlunoResponseDTO alunoAtualizado = alunoService.atualizarAluno(id, dto);
        return ResponseEntity.ok(alunoAtualizado);
    }

    @PreAuthorize("hasRole('COORDENADOR')")
    @DeleteMapping("/excluir/{id}")
    @Operation(
            summary = "Excluir Aluno (soft delete)",
            description = "Marca o aluno como excluído (`excluido = true`). **Permissão**: COORDENADOR. Retorna 204."
    )
    public ResponseEntity<Void> deletarAluno(@PathVariable Long id) {
        alunoService.deletarAluno(id); // soft delete no service
        return ResponseEntity.noContent().build();
    }
}
