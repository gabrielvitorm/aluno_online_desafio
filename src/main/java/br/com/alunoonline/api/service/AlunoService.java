package br.com.alunoonline.api.service;

import br.com.alunoonline.api.dto.AlunoRequestDTO;
import br.com.alunoonline.api.dto.AlunoResponseDTO;

import java.util.List;

public interface AlunoService {

    AlunoResponseDTO criarAluno(AlunoRequestDTO dto);

    List<AlunoResponseDTO> listarAlunos();

    AlunoResponseDTO listarAlunoPorId(Long id);

    AlunoResponseDTO atualizarAluno(Long id, AlunoRequestDTO dto);

    void deletarAluno(Long id);
}
