package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.dto.AlunoRequestDTO;
import br.com.alunoonline.api.dto.AlunoResponseDTO;
import br.com.alunoonline.api.service.AlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @PostMapping
    public ResponseEntity<AlunoResponseDTO> criarAluno(@Valid @RequestBody AlunoRequestDTO dto) {
        AlunoResponseDTO alunoCriado = alunoService.criarAluno(dto);

        return ResponseEntity.status(201).body(alunoCriado);
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponseDTO>> listarAlunos() {
        return ResponseEntity.ok(alunoService.listarAlunos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> listarAlunoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(alunoService.listarAlunoPorId(id));
    }

    @PutMapping("/editar-aluno/{id}")
    public ResponseEntity<AlunoResponseDTO> atualizarAluno(
            @PathVariable Long id,
            @Valid @RequestBody AlunoRequestDTO dto
    ) {
        AlunoResponseDTO alunoAtualizado = alunoService.atualizarAluno(id, dto);

        return ResponseEntity.ok(alunoAtualizado);
    }

    @PatchMapping("/excluir/{id}")
    public ResponseEntity<AlunoResponseDTO> deletarAluno(@PathVariable Long id) {
        return ResponseEntity.ok(alunoService.deletarAluno(id));
    }
}
