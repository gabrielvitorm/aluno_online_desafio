package br.com.alunoonline.api.service;

import br.com.alunoonline.api.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatriculaDisciplinaServiceImpl implements MatriculaDisciplinaService {
    @Override
    public MatriculaDisciplinaResponseDTO criarMatricula(MatriculaDisciplinaRequestDTO dto) {
        return null;
    }

    @Override
    public List<MatriculaDisciplinaResponseDTO> listarMatriculas() {
        return List.of();
    }

    @Override
    public MatriculaDisciplinaResponseDTO listarPorId(Long id) {
        return null;
    }

    @Override
    public MatriculaDisciplinaResponseDTO atualizarNotas(Long id, AtualizarNotasRequestDTO dto) {
        return null;
    }

    @Override
    public MatriculaDisciplinaResponseDTO trancarDisciplina(Long id) {
        return null;
    }

    @Override
    public MatriculaDisciplinaResponseDTO atualizarMatricula(Long id, MatriculaDisciplinaRequestDTO dto) {
        return null;
    }

    @Override
    public MatriculaDisciplinaResponseDTO emitirHistorico(Long id) {
        return null;
    }

//    public static final double MEDIA_PARA_APROVACAO = 7.0;
//    public static final int QNT_NOTAS = 2;
//
//    private final MatriculaDisciplinaRepository matriculaDisciplinaRepository;
//    private final MatriculaCursoMapper matriculaCursoMapper;
//    private final AlunoRepository alunoRepository;
//    private final DisciplinaRepository disciplinaRepository;
//
//    @Transactional
//    @Override
//    public MatriculaCursoResponseDTO criarMatricula(MatriculaCursoRequestDTO dto) {
//        MatriculaCurso matriculaCurso = matriculaCursoMapper.toEntity(dto);
//
//        if (matriculaDisciplinaRepository.existsByAlunoIdAndDisciplinaIdAndIdNot(dto.alunoId(), dto.disciplinaId(), matriculaCurso.getId())) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT,
//                    "Aluno já matriculado nesta disciplina.");
//        }
//
//        matriculaCurso.setAluno(buscarAluno(dto.alunoId()));
//        matriculaCurso.setStatus(MatriculaCursoStatusEnum.MATRICULADO);
//
//        return matriculaCursoMapper.toDTO(matriculaDisciplinaRepository.save(matriculaCurso));
//    }
//
//    @Override
//    public List<MatriculaCursoResponseDTO> listarMatriculas() {
//        return matriculaDisciplinaRepository.findAll().stream()
//                .map(matriculaCursoMapper::toDTO)
//                .toList();
//    }
//
//    @Override
//    public MatriculaCursoResponseDTO listarMatriculaPorId(Long id) {
//        MatriculaCurso matriculaCurso = matriculaDisciplinaRepository.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
//                        "Matrícula não encontrada"));
//
//        return matriculaCursoMapper.toDTO(matriculaCurso);
//    }
//
//    @Transactional
//    @Override
//    public MatriculaCursoResponseDTO atualizarMatricula(Long id, MatriculaCursoRequestDTO dto) {
//        MatriculaCurso matriculaCurso = buscarMatricula(id);
//
//        matriculaCurso.setAluno(buscarAluno(dto.alunoId()));
//
//        return matriculaCursoMapper.toDTO(matriculaDisciplinaRepository.save(matriculaCurso));
//    }
//
//    @Transactional
//    @Override
//    public MatriculaCursoResponseDTO trancarMatricula(Long id) {
//        MatriculaCurso matriculaCurso = buscarMatricula(id);
//
//        if (!matriculaCurso.getStatus().equals(MatriculaCursoStatusEnum.MATRICULADO)) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//                    "Só é possível trancar o curso com o status MATRICULADO");
//        }
//
//        if (matriculaCurso.getStatus() == MatriculaCursoStatusEnum.APROVADO || matriculaCurso.getStatus() == MatriculaCursoStatusEnum.REPROVADO) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//                    "Não é possível trancar matrícula já finalizada.");
//        }
//
//        matriculaCurso.setStatus(MatriculaCursoStatusEnum.TRANCADO);
//
//        return matriculaCursoMapper.toDTO(matriculaDisciplinaRepository.save(matriculaCurso));
//    }
//
//    @Transactional
//    @Override
//    public MatriculaCursoResponseDTO atualizarNotas(Long id, AtualizarNotasRequestDTO dto) {
//        MatriculaCurso matriculaCurso = buscarMatricula(id);
//
//        validarStatusMatricula(matriculaCurso);
//
//        validarNotas(dto.nota1(), dto.nota2());
//
//        matriculaCurso.setNota1(dto.nota1());
//        matriculaCurso.setNota2(dto.nota2());
//
//        double media = calcularMedia(matriculaCurso.getNota1(), matriculaCurso.getNota2());
//
//        alterarStatus(matriculaCurso, media);
//
//        return matriculaCursoMapper.toDTO(matriculaDisciplinaRepository.save(matriculaCurso));
//    }
//
//    @Transactional
//    @Override
//    public HistoricoAlunoResponseDTO emitirHistorico(Long id) {
//        MatriculaCurso matriculaCurso = buscarMatricula(id);
//
//        validarNotas(matriculaCurso.getNota1(), matriculaCurso.getNota2());
//
//        double media = calcularMedia(matriculaCurso.getNota1(), matriculaCurso.getNota2());
//
//        validarStatusMatricula(matriculaCurso);
//
//        alterarStatus(matriculaCurso, media);
//
//        matriculaDisciplinaRepository.save(matriculaCurso);
//
//        return matriculaCursoMapper.toHistoricoDTO(matriculaCurso, media);
//    }
//
//    private MatriculaCurso buscarMatricula(Long id) {
//        return matriculaDisciplinaRepository.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
//                        "Matrícula não encontrada"));
//    }
//
//    private Aluno buscarAluno(Long id) {
//        return alunoRepository.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
//                        "Aluno não encontrado"));
//    }
//
//    private Disciplina buscarDisciplina(Long id) {
//        return disciplinaRepository.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
//                        "Disciplina não encontrada"));
//    }
//
//    private void validarNotas(Double nota1, Double nota2) {
//        if (nota1 == null || nota2 == null) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//                    "As duas notas são obrigatórias.");
//        }
//        if (nota1 < 0 || nota1 > 10 || nota2 < 0 || nota2 > 10) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//                    "Notas devem estar entre 0 e 10.");
//        }
//    }
//
//    private double calcularMedia(Double nota1, Double nota2) {
//        return (nota1 + nota2) / QNT_NOTAS;
//    }
//
//    private void alterarStatus(MatriculaCurso matriculaCurso, Double media) {
//        matriculaCurso.setStatus(
//                media >= MEDIA_PARA_APROVACAO
//                        ? MatriculaCursoStatusEnum.APROVADO
//                        : MatriculaCursoStatusEnum.REPROVADO);
//    }
//
//    private void validarStatusMatricula(MatriculaCurso matriculaCurso) {
//        if (matriculaCurso.getStatus().equals(MatriculaCursoStatusEnum.TRANCADO)) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//                    "Não é permitido fazer essa ação quando o status não é MATRICULADO.");
//        }
//    }
}
