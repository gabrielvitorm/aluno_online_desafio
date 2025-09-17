package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.dto.MatriculaDisciplinaRequestDTO;
import br.com.alunoonline.api.dto.MatriculaDisciplinaResponseDTO;
import br.com.alunoonline.api.service.MatriculaDisciplinaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matricula-disciplina")
@RequiredArgsConstructor
public class MatriculaDisciplinaController {

    private final MatriculaDisciplinaService matriculaDisciplinaService;

    @PostMapping
    public ResponseEntity<MatriculaDisciplinaResponseDTO> criarMatricula(@Valid @RequestBody MatriculaDisciplinaRequestDTO dto) {
        MatriculaDisciplinaResponseDTO matriculaCriada = matriculaDisciplinaService.criarMatricula(dto);

        return ResponseEntity.status(201).body(matriculaCriada);
    }

}
