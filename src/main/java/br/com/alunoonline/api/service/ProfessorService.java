package br.com.alunoonline.api.service;

import br.com.alunoonline.api.dto.ProfessorRequestDTO;
import br.com.alunoonline.api.dto.ProfessorResponseDTO;

import java.util.List;

public interface ProfessorService {

    ProfessorResponseDTO criarProfessor(ProfessorRequestDTO dto);

    List<ProfessorResponseDTO> listarProfessores();

    ProfessorResponseDTO listarProfessorPorId(Long id);

    ProfessorResponseDTO atualizarProfessor(Long id, ProfessorRequestDTO dto);

    ProfessorResponseDTO deletarProfessor(Long id);
}
