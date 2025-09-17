package br.com.alunoonline.api.service;

import br.com.alunoonline.api.dto.AlunoRequestDTO;
import br.com.alunoonline.api.dto.AlunoResponseDTO;
import br.com.alunoonline.api.mapper.AlunoMapper;
import br.com.alunoonline.api.mapper.EnderecoMapper;
import br.com.alunoonline.api.model.Aluno;
import br.com.alunoonline.api.model.Curso;
import br.com.alunoonline.api.repository.AlunoRepository;
import br.com.alunoonline.api.repository.CursoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoServiceImpl implements AlunoService {

    private final AlunoRepository alunoRepository;
    private final AlunoMapper alunoMapper;
    private final EnderecoMapper enderecoMapper;
    private final CursoRepository cursoRepository;

    @Transactional
    @Override
    public AlunoResponseDTO criarAluno(AlunoRequestDTO dto) {
        Aluno aluno = alunoMapper.toEntity(dto);

        aluno.setCurso(buscarCurso(dto.cursoId()));
        aluno.setExcluido(false);
        aluno.setEndereco(enderecoMapper.toEntity(dto.endereco()));

        return alunoMapper.toDTO(alunoRepository.save(aluno));
    }

    @Override
    public List<AlunoResponseDTO> listarAlunos() {
        return alunoRepository.findAll().stream()
                .map(alunoMapper::toDTO)
                .toList();
    }

    @Override
    public AlunoResponseDTO listarAlunoPorId(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Aluno não encontrado!"));

        return alunoMapper.toDTO(aluno);
    }

    @Transactional
    @Override
    public AlunoResponseDTO atualizarAluno(Long id, AlunoRequestDTO dto) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Aluno não encontrado!"));

        aluno.setNome(dto.nome());
        aluno.setEmail(dto.email());
        aluno.setRg(dto.rg());
        aluno.setCpf(dto.cpf());
        aluno.setTelefone(dto.telefone());
        aluno.setIdade(dto.idade());
        aluno.setGenero(dto.genero());
        aluno.setCurso(buscarCurso(dto.cursoId()));
        aluno.setEndereco(enderecoMapper.toEntity(dto.endereco()));

        return alunoMapper.toDTO(alunoRepository.save(aluno));
    }

    @PreAuthorize("hasRole('COORDENADOR')")
    @Transactional
    @Override
    public AlunoResponseDTO deletarAluno(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Aluno não existe!"));

        aluno.setExcluido(true);

        return alunoMapper.toDTO(alunoRepository.save(aluno));
    }

    private Curso buscarCurso(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Curso não encontrado"));
    }
}
