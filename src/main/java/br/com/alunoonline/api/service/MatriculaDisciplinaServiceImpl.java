package br.com.alunoonline.api.service;

import br.com.alunoonline.api.dto.*;
import br.com.alunoonline.api.enums.MatriculaCursoStatusEnum;
import br.com.alunoonline.api.enums.SituacaoStatusEnum;
import br.com.alunoonline.api.mapper.MatriculaDisciplinaMapper;
import br.com.alunoonline.api.model.Aluno;
import br.com.alunoonline.api.model.Disciplina;
import br.com.alunoonline.api.model.MatriculaCurso;
import br.com.alunoonline.api.model.MatriculaDisciplina;
import br.com.alunoonline.api.repository.AlunoRepository;
import br.com.alunoonline.api.repository.DisciplinaRepository;
import br.com.alunoonline.api.repository.MatriculaCursoRepository;
import br.com.alunoonline.api.repository.MatriculaDisciplinaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatriculaDisciplinaServiceImpl implements MatriculaDisciplinaService {

    public static final double MEDIA_PARA_APROVACAO = 7.0;
    public static final int QNT_NOTAS = 2;

    private final MatriculaDisciplinaRepository matriculaDisciplinaRepository;
    private final AlunoRepository alunoRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final MatriculaCursoRepository matriculaCursoRepository;
    private final MatriculaDisciplinaMapper matriculaDisciplinaMapper;

    @Transactional
    @Override
    public MatriculaDisciplinaResponseDTO criarMatricula(MatriculaDisciplinaRequestDTO dto) {
        MatriculaDisciplina matriculaDisciplina = matriculaDisciplinaMapper.toEntity(dto);

        matriculaDisciplina.setAluno(buscarAluno(dto.alunoId()));
        matriculaDisciplina.setDisciplina(buscarDisciplina(dto.disciplinaId()));
        matriculaDisciplina.setMatriculaCurso(buscarCurso(dto.matriculaCursoId()));
        matriculaDisciplina.setMatriculaStatus(MatriculaCursoStatusEnum.MATRICULADO);

        return matriculaDisciplinaMapper.toDTO(matriculaDisciplinaRepository.save(matriculaDisciplina));
    }

    @Override
    public List<MatriculaDisciplinaResponseDTO> listarMatriculas() {
        return matriculaDisciplinaRepository.findAll().stream()
                .map(matriculaDisciplinaMapper::toDTO)
                .toList();
    }

    @Override
    public MatriculaDisciplinaResponseDTO listarPorId(Long id) {
        MatriculaDisciplina matriculaDisciplina = matriculaDisciplinaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Matricula não encontrada"));

        return matriculaDisciplinaMapper.toDTO(matriculaDisciplina);
    }

    @Transactional
    @Override
    public MatriculaDisciplinaResponseDTO atualizarNotas(Long id, AtualizarNotasRequestDTO dto) {
        MatriculaDisciplina matriculaDisciplina = matriculaDisciplinaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Matricula não encontrada"));

        validarNotas(dto.nota1(), dto.nota2());

        matriculaDisciplina.setNota1(dto.nota1());
        matriculaDisciplina.setNota2(dto.nota2());

        return matriculaDisciplinaMapper.toDTO(matriculaDisciplinaRepository.save(matriculaDisciplina));

    }

    @Transactional
    @Override
    public MatriculaDisciplinaResponseDTO trancarDisciplina(Long id) {
        MatriculaDisciplina matriculaDisciplina = matriculaDisciplinaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Matricula não encontrada"));

        validarStatusMatricula(matriculaDisciplina);

        matriculaDisciplina.setMatriculaStatus(MatriculaCursoStatusEnum.TRANCADO);

        return matriculaDisciplinaMapper.toDTO(matriculaDisciplinaRepository.save(matriculaDisciplina));
    }

    @Transactional
    @Override
    public MatriculaDisciplinaResponseDTO atualizarMatricula(Long id, MatriculaDisciplinaRequestDTO dto) {
        return null;
    }

    @Transactional
    @Override
    public HistoricoAlunoResponseDTO emitirHistorico(Long id) {
        MatriculaDisciplina matriculaDisciplina = matriculaDisciplinaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Matrícula não encontrada"));


        double media = calcularMedia(matriculaDisciplina.getNota1(),matriculaDisciplina.getNota2());

        alterarStatus(matriculaDisciplina, media);

        validarStatusMatricula(matriculaDisciplina);

        alterarStatus(matriculaDisciplina, media);

        matriculaDisciplinaRepository.save(matriculaDisciplina);

        return matriculaDisciplinaMapper.toHistoricoDTO(matriculaDisciplina, media);
    }

    private Aluno buscarAluno(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Aluno não encontrado"));
    }

    private Disciplina buscarDisciplina(Long id) {
        return disciplinaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Disciplina não encontrada"));
    }

    private MatriculaCurso buscarCurso(Long id) {
        return matriculaCursoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Curso não encontrado"));
    }

    private void validarNotas(Double nota1, Double nota2) {
        if (nota1 == null || nota2 == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "As duas notas são obrigatórias.");
        }
        if (nota1 < 0 || nota1 > 10 || nota2 < 0 || nota2 > 10) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Notas devem estar entre 0 e 10.");
        }
    }

    private double calcularMedia(Double nota1, Double nota2) {
        return (nota1 + nota2) / QNT_NOTAS;
    }

    private void alterarStatus(MatriculaDisciplina matriculaDisciplina, Double media) {
        matriculaDisciplina.setStatus(
                media >= MEDIA_PARA_APROVACAO
                        ? SituacaoStatusEnum.APROVADO
                        : SituacaoStatusEnum.REPROVADO
                );
    }

    private void validarStatusMatricula(MatriculaDisciplina matriculaDisciplina) {
        if (matriculaDisciplina.getStatus().equals(SituacaoStatusEnum.TRANCADO)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Não é permitido fazer essa ação quando o status não é MATRICULADO.");
        }

        if (matriculaDisciplina.getStatus() == SituacaoStatusEnum.APROVADO || matriculaDisciplina.getStatus() == SituacaoStatusEnum.REPROVADO) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Não é possível trancar matrícula já finalizada.");
        }
    }
}
