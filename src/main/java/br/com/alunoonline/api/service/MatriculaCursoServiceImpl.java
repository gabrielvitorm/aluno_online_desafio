package br.com.alunoonline.api.service;

import br.com.alunoonline.api.dto.MatriculaCursoRequestDTO;
import br.com.alunoonline.api.dto.MatriculaCursoResponseDTO;
import br.com.alunoonline.api.enums.MatriculaCursoStatusEnum;
import br.com.alunoonline.api.mapper.MatriculaCursoMapper;
import br.com.alunoonline.api.model.Aluno;
import br.com.alunoonline.api.model.Curso;
import br.com.alunoonline.api.model.MatriculaCurso;
import br.com.alunoonline.api.repository.AlunoRepository;
import br.com.alunoonline.api.repository.CursoRepository;
import br.com.alunoonline.api.repository.MatriculaCursoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatriculaCursoServiceImpl implements MatriculaCursoService {

    private final MatriculaCursoRepository matriculaCursoRepository;
    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;
    private final MatriculaCursoMapper matriculaCursoMapper;

    @Transactional
    @Override
    public MatriculaCursoResponseDTO criarMatricula(MatriculaCursoRequestDTO dto) {
        if (matriculaCursoRepository.existsByAlunoIdAndCursoId(dto.alunoId(), dto.cursoId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Aluno já possui matrícula neste curso.");
        }

        MatriculaCurso matriculaCurso = matriculaCursoMapper.toEntity(dto);

        matriculaCurso.setAluno(buscarAluno(dto.alunoId()));
        matriculaCurso.setCurso(buscarCurso(dto.cursoId()));
        matriculaCurso.setStatus(MatriculaCursoStatusEnum.MATRICULADO);
        matriculaCurso.setExcluido(false);

        return matriculaCursoMapper.toDTO(matriculaCursoRepository.save(matriculaCurso));
    }

    @Override
    public List<MatriculaCursoResponseDTO> listarMatriculas() {
        return matriculaCursoRepository.findAll().stream()
                .map(matriculaCursoMapper::toDTO)
                .toList();
    }

    @Override
    public MatriculaCursoResponseDTO listarPorId(Long id) {
        MatriculaCurso matriculaCurso = matriculaCursoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Matricula não encontrada!"));

        return matriculaCursoMapper.toDTO(matriculaCurso);
    }

    @Transactional
    @Override
    public MatriculaCursoResponseDTO trancarMatricula(Long id) {
        MatriculaCurso matriculaCurso = matriculaCursoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Matricula não encontrada!"));

        validarStatusParaTrancar(matriculaCurso);

        matriculaCurso.setStatus(MatriculaCursoStatusEnum.TRANCADO);
        matriculaCurso.setDataTrancamento(LocalDateTime.now());

        return matriculaCursoMapper.toDTO(matriculaCursoRepository.save(matriculaCurso));
    }

    @Transactional
    @Override
    public MatriculaCursoResponseDTO reativarMatricula(Long id) {
        MatriculaCurso matriculaCurso = matriculaCursoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Matricula não encontrada!"));

        validarStatusParaReativar(matriculaCurso);

        matriculaCurso.setStatus(MatriculaCursoStatusEnum.MATRICULADO);

        return matriculaCursoMapper.toDTO(matriculaCursoRepository.save(matriculaCurso));
    }

    @Transactional
    @Override
    public MatriculaCursoResponseDTO concluirMatricula(Long id) {
        MatriculaCurso matriculaCurso = matriculaCursoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Matricula não encontrada!"));

        if (matriculaCurso.getStatus() != MatriculaCursoStatusEnum.MATRICULADO) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O curso só pode ser concluído quando o aluno estiver MATRICULADO.");
        }

        matriculaCurso.setStatus(MatriculaCursoStatusEnum.CONCLUIDO);
        matriculaCurso.setDataConclusão(LocalDateTime.now());

        return matriculaCursoMapper.toDTO(matriculaCursoRepository.save(matriculaCurso));
    }

    @Transactional
    @Override
    public MatriculaCursoResponseDTO atualizarMatricula(Long id, MatriculaCursoRequestDTO dto) {
        MatriculaCurso matriculaCurso = matriculaCursoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Matricula não encontrada!"));

        matriculaCurso.setAluno(buscarAluno(dto.alunoId()));
        matriculaCurso.setCurso(buscarCurso(dto.cursoId()));

        return matriculaCursoMapper.toDTO(matriculaCursoRepository.save(matriculaCurso));
    }

    @Transactional
    @Override
    public MatriculaCursoResponseDTO deletarMatricula(Long id) {
        MatriculaCurso matriculaCurso = matriculaCursoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Matricula não encontrada!"));

        matriculaCurso.setExcluido(true);

        return matriculaCursoMapper.toDTO(matriculaCurso);
    }

    private Aluno buscarAluno(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Aluno não encontrado!"));
    }

    private Curso buscarCurso(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Curso não encontrado"));
    }

    private void validarStatusParaTrancar(MatriculaCurso matriculaCurso) {
        if (matriculaCurso.getStatus() != MatriculaCursoStatusEnum.MATRICULADO) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Só é possível trancar quando o status é MATRICULADO.");
        }
    }

    private void validarStatusParaReativar(MatriculaCurso matriculaCurso) {
        if (matriculaCurso.getStatus() != MatriculaCursoStatusEnum.TRANCADO) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Só é possível reativar quando o status é TRANCADO.");
        }
    }
}
