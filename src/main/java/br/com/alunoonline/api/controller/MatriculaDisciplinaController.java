package br.com.alunoonline.api.controller;

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
}
