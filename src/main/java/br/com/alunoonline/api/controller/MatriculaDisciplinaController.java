package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.dto.AtualizarNotasRequestDTO;
import br.com.alunoonline.api.dto.HistoricoAlunoResponseDTO;
import br.com.alunoonline.api.dto.MatriculaCursoRequestDTO;
import br.com.alunoonline.api.dto.MatriculaCursoResponseDTO;
import br.com.alunoonline.api.service.MatriculaDisciplinaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matricula-curso")
@RequiredArgsConstructor
public class MatriculaDisciplinaController {

//    private final MatriculaDisciplinaService matriculaDisciplinaService;
//
//    @PostMapping
//    public ResponseEntity<MatriculaCursoResponseDTO> criarMatricula(@Valid @RequestBody MatriculaCursoRequestDTO dto) {
//        MatriculaCursoResponseDTO matriculaCriada = matriculaDisciplinaService.criarMatricula(dto);
//
//        return ResponseEntity.status(201).body(matriculaCriada);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<MatriculaCursoResponseDTO>> listarMatriculas() {
//        return ResponseEntity.ok(matriculaDisciplinaService.listarMatriculas());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<MatriculaCursoResponseDTO> listarMatriculaPorId(@PathVariable Long id) {
//        return ResponseEntity.ok(matriculaDisciplinaService.listarMatriculaPorId(id));
//    }
//
//    @PutMapping("/atualizar/{id}")
//    public ResponseEntity<MatriculaCursoResponseDTO> atualizarMatricula(
//            @PathVariable Long id,
//            @Valid @RequestBody MatriculaCursoRequestDTO dto
//    ) {
//        MatriculaCursoResponseDTO matriculaEditada = matriculaDisciplinaService.atualizarMatricula(id, dto);
//
//        return ResponseEntity.ok(matriculaEditada);
//    }
//
//    @PatchMapping("/trancar/{id}")
//    public ResponseEntity<MatriculaCursoResponseDTO> trancarMatricula(@PathVariable Long id) {
//        return ResponseEntity.ok(matriculaDisciplinaService.trancarMatricula(id));
//    }
//
//    @PatchMapping("/atualizar-notas/{id}")
//    public ResponseEntity<MatriculaCursoResponseDTO> atualizarNotas(
//            @PathVariable Long id,
//            @Valid @RequestBody AtualizarNotasRequestDTO dto
//    ) {
//        return ResponseEntity.ok(matriculaDisciplinaService.atualizarNotas(id, dto));
//    }
//
//    @GetMapping("/emitir-historico/{id}")
//    public ResponseEntity<HistoricoAlunoResponseDTO> emitirHistorico(@PathVariable Long id) {
//        return ResponseEntity.ok(matriculaDisciplinaService.emitirHistorico(id));
//    }

}
