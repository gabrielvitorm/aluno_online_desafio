package br.com.alunoonline.api.service;

import br.com.alunoonline.api.dto.MatriculaCursoRequestDTO;
import br.com.alunoonline.api.dto.MatriculaCursoResponseDTO;

import java.util.List;

public interface MatriculaCursoService {

    MatriculaCursoResponseDTO criarMatricula(MatriculaCursoRequestDTO dto);

    List<MatriculaCursoResponseDTO> listarMatriculas();

    MatriculaCursoResponseDTO listarPorId(Long id);

    MatriculaCursoResponseDTO trancarMatricula(Long id);

    MatriculaCursoResponseDTO reativarMatricula(Long id);

    MatriculaCursoResponseDTO concluirMatricula(Long id);

    MatriculaCursoResponseDTO atualizarMatricula(Long id, MatriculaCursoRequestDTO dto);

    void deletarMatricula(Long id);
}
