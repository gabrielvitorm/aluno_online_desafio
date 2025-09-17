package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.dto.ProfessorRequestDTO;
import br.com.alunoonline.api.dto.ProfessorResponseDTO;
import br.com.alunoonline.api.service.ProfessorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService professorService;

    @PostMapping
    public ResponseEntity<ProfessorResponseDTO> criarProfessor(@Valid @RequestBody ProfessorRequestDTO dto) {
        ProfessorResponseDTO professorCriado = professorService.criarProfessor(dto);

        return ResponseEntity.status(201).body(professorCriado);
    }

    @GetMapping
    public ResponseEntity<List<ProfessorResponseDTO>> listarProfessores() {
        return ResponseEntity.ok(professorService.listarProfessores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponseDTO> listarProfessorPorId(@PathVariable Long id) {
        return ResponseEntity.ok(professorService.listarProfessorPorId(id));
    }

    @PutMapping("/editar-professor/{id}")
    public ResponseEntity<ProfessorResponseDTO> atualizarProfessor(
            @PathVariable Long id,
            @Valid @RequestBody ProfessorRequestDTO dto
    ) {
        ProfessorResponseDTO professorAtualizado = professorService.atualizarProfessor(id, dto);

        return ResponseEntity.ok(professorAtualizado);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarProfessor(@PathVariable Long id) {
        professorService.deletarProfessor(id);

        return ResponseEntity.noContent().build();
    }
}
