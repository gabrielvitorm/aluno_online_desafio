package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.dto.CursoRequestDTO;
import br.com.alunoonline.api.dto.CursoResponseDTO;
import br.com.alunoonline.api.service.CursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @PostMapping
    public ResponseEntity<CursoResponseDTO> criarCurso(@Valid @RequestBody CursoRequestDTO dto) {
        CursoResponseDTO cursoCriado = cursoService.criarCurso(dto);

        return ResponseEntity.status(201).body(cursoCriado);
    }

    @GetMapping
    public ResponseEntity<List<CursoResponseDTO>> listarCursos() {
        return ResponseEntity.ok(cursoService.listarCursos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> listarCursoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cursoService.listarCursoPorId(id));
    }

    @PutMapping("/editar-curso/{id}")
    public ResponseEntity<CursoResponseDTO> atualizarCurso(
            @PathVariable Long id,
            @Valid @RequestBody CursoRequestDTO dto
    ) {
        CursoResponseDTO cursoEditado = cursoService.atualizarCurso(id, dto);

        return ResponseEntity.ok(cursoEditado);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<CursoResponseDTO> excluirCurso(@PathVariable Long id) {
        CursoResponseDTO cursoExcluido = cursoService.deletarCurso(id);

        return ResponseEntity.ok(cursoExcluido);
    }
}
