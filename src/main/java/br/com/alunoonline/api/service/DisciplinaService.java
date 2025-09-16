package br.com.alunoonline.api.service;

import br.com.alunoonline.api.dto.DisciplinaRequestDTO;
import br.com.alunoonline.api.dto.DisciplinaResponseDTO;

import java.util.List;

public interface DisciplinaService {

    DisciplinaResponseDTO criarDisciplina(DisciplinaRequestDTO dto);

    List<DisciplinaResponseDTO> listarDisciplinas();

    DisciplinaResponseDTO listarDisciplinaPorId(Long id);

    DisciplinaResponseDTO atualizarDisciplina(Long id, DisciplinaRequestDTO dto);

    DisciplinaResponseDTO deletarDisciplina(Long id);
}
