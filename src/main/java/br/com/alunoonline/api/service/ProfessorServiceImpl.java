package br.com.alunoonline.api.service;

import br.com.alunoonline.api.dto.ProfessorRequestDTO;
import br.com.alunoonline.api.dto.ProfessorResponseDTO;
import br.com.alunoonline.api.mapper.EnderecoMapper;
import br.com.alunoonline.api.mapper.ProfessorMapper;
import br.com.alunoonline.api.model.Professor;
import br.com.alunoonline.api.repository.ProfessorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorServiceImpl implements ProfessorService{

    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;
    private final EnderecoMapper enderecoMapper;

    @Override
    public ProfessorResponseDTO criarProfessor(ProfessorRequestDTO dto) {
        Professor professor = professorMapper.toEntity(dto);

        professor.setExcluido(false);

        return professorMapper.toDTO(professorRepository.save(professor));
    }

    @Override
    public List<ProfessorResponseDTO> listarProfessores() {
        return professorRepository.findAll().stream()
                .map(professorMapper::toDTO)
                .toList();
    }

    @Override
    public ProfessorResponseDTO listarProfessorPorId(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Professor não encontrado"));

        return professorMapper.toDTO(professor);
    }

    @Transactional
    @Override
    public ProfessorResponseDTO atualizarProfessor(Long id, ProfessorRequestDTO dto) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Professor não encontrado"));

        professor.setNome(dto.nome());
        professor.setEmail(dto.email());
        professor.setRg(dto.rg());
        professor.setCpf(dto.cpf());
        professor.setTelefone(dto.telefone());
        professor.setIdade(dto.idade());
        professor.setGenero(dto.genero());
        professor.setEndereco(enderecoMapper.toEntity(dto.endereco()));

        return professorMapper.toDTO(professorRepository.save(professor));
    }

    @Transactional
    @Override
    public void deletarProfessor(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Professor não encontrado"));

        professor.setExcluido(true);

        professorRepository.save(professor);
    }
}
