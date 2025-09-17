package br.com.alunoonline.api.service;

import br.com.alunoonline.api.dto.CursoRequestDTO;
import br.com.alunoonline.api.dto.CursoResponseDTO;

import java.util.List;

public interface CursoService {

    CursoResponseDTO criarCurso(CursoRequestDTO dto);

    List<CursoResponseDTO> listarCursos();

    CursoResponseDTO listarCursoPorId(Long id);

    CursoResponseDTO atualizarCurso(Long id, CursoRequestDTO dto);

    void deletarCurso(Long id);
}
