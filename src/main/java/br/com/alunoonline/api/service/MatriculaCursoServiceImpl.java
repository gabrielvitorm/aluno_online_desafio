package br.com.alunoonline.api.service;

import br.com.alunoonline.api.dto.MatriculaCursoRequestDTO;
import br.com.alunoonline.api.dto.MatriculaCursoResponseDTO;

import java.util.List;

public class MatriculaCursoServiceImpl implements MatriculaCursoService {
    @Override
    public MatriculaCursoResponseDTO criarMatricula(MatriculaCursoRequestDTO dto) {
        return null;
    }

    @Override
    public List<MatriculaCursoResponseDTO> listarMatriculas() {
        return List.of();
    }

    @Override
    public MatriculaCursoResponseDTO listarPorId(Long id) {
        return null;
    }

    @Override
    public MatriculaCursoResponseDTO trancarMatricula(Long id) {
        return null;
    }

    @Override
    public MatriculaCursoResponseDTO reativarMatricula(Long id) {
        return null;
    }

    @Override
    public MatriculaCursoResponseDTO concluirMatricula(Long id) {
        return null;
    }
}
