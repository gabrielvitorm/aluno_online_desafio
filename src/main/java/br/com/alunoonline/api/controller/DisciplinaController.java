package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.dto.DisciplinaRequestDTO;
import br.com.alunoonline.api.dto.DisciplinaResponseDTO;
import br.com.alunoonline.api.service.DisciplinaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplinas")
@RequiredArgsConstructor
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    @PostMapping
    public ResponseEntity<DisciplinaResponseDTO> criarDisciplina(@Valid @RequestBody DisciplinaRequestDTO dto) {
        DisciplinaResponseDTO disciplinaCriada = disciplinaService.criarDisciplina(dto);

        return ResponseEntity.status(201).body(disciplinaCriada);
    }

    @GetMapping
    public ResponseEntity<List<DisciplinaResponseDTO>> listarDisciplinas() {
        return ResponseEntity.ok(disciplinaService.listarDisciplinas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaResponseDTO> listarDisciplinaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(disciplinaService.listarDisciplinaPorId(id));
    }

    @PutMapping("/editar-disciplina/{id}")
    public ResponseEntity<DisciplinaResponseDTO> atualizarDisciplina(
            @PathVariable Long id,
            @Valid @RequestBody DisciplinaRequestDTO dto
    ) {
        DisciplinaResponseDTO disciplinaEditada = disciplinaService.atualizarDisciplina(id, dto);

        return ResponseEntity.ok(disciplinaEditada);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> deletarDisciplina(@PathVariable Long id) {
        disciplinaService.deletarDisciplina(id);

        return ResponseEntity.noContent().build();
    }
}
