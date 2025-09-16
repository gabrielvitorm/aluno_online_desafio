package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.dto.MatriculaCursoRequestDTO;
import br.com.alunoonline.api.dto.MatriculaCursoResponseDTO;
import br.com.alunoonline.api.service.MatriculaCursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matricula-curso")
@RequiredArgsConstructor
public class MatriculaCursoController {

    private final MatriculaCursoService matriculaCursoService;

    @PostMapping
    public ResponseEntity<MatriculaCursoResponseDTO> criarMatricula(@Valid @RequestBody MatriculaCursoRequestDTO dto) {
        MatriculaCursoResponseDTO matriculaAdicionada = matriculaCursoService.criarMatricula(dto);

        return ResponseEntity.status(201).body(matriculaAdicionada);
    }

    @GetMapping
    public ResponseEntity<List<MatriculaCursoResponseDTO>> listarMatriculas() {
        return ResponseEntity.ok(matriculaCursoService.listarMatriculas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaCursoResponseDTO> listarMatriculaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(matriculaCursoService.listarPorId(id));
    }

    @PatchMapping("/trancar/{id}")
    public ResponseEntity<MatriculaCursoResponseDTO> trancarMatricula(@PathVariable Long id) {
        return ResponseEntity.ok(matriculaCursoService.trancarMatricula(id));
    }

    @PatchMapping("/reativar/{id}")
    public ResponseEntity<MatriculaCursoResponseDTO> reativarMatricula(@PathVariable Long id) {
        return ResponseEntity.ok(matriculaCursoService.reativarMatricula(id));
    }

    @PatchMapping("/concluir/{id}")
    public ResponseEntity<MatriculaCursoResponseDTO> concluirMatricula(@PathVariable Long id) {
        return ResponseEntity.ok(matriculaCursoService.concluirMatricula(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatriculaCursoResponseDTO> atualizarMatricula(
            @PathVariable Long id,
            @Valid @RequestBody MatriculaCursoRequestDTO dto
    ) {
        MatriculaCursoResponseDTO matriculaEditada = matriculaCursoService.atualizarMatricula(id, dto);

        return ResponseEntity.ok(matriculaEditada);
    }

    @PatchMapping("/deletar/{id}")
    public ResponseEntity<MatriculaCursoResponseDTO> deletarMatricula(@PathVariable Long id) {
        return ResponseEntity.ok(matriculaCursoService.deletarMatricula(id));
    }
}
