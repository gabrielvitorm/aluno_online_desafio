package br.com.alunoonline.api.service;

import br.com.alunoonline.api.dto.*;

import java.util.List;

public interface MatriculaDisciplinaService {

    MatriculaDisciplinaResponseDTO criarMatricula(MatriculaDisciplinaRequestDTO dto);

    List<MatriculaDisciplinaResponseDTO> listarMatriculas();

    MatriculaDisciplinaResponseDTO listarPorId(Long id);

    MatriculaDisciplinaResponseDTO atualizarNotas(Long id, AtualizarNotasRequestDTO dto);

    MatriculaDisciplinaResponseDTO trancarDisciplina(Long id);

    MatriculaDisciplinaResponseDTO atualizarMatricula(Long id, MatriculaDisciplinaRequestDTO dto);

    HistoricoAlunoResponseDTO emitirHistorico(Long id);
}
