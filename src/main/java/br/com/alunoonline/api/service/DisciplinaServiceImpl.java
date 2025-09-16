package br.com.alunoonline.api.service;

import br.com.alunoonline.api.dto.DisciplinaRequestDTO;
import br.com.alunoonline.api.dto.DisciplinaResponseDTO;
import br.com.alunoonline.api.mapper.DisciplinaMapper;
import br.com.alunoonline.api.model.Disciplina;
import br.com.alunoonline.api.model.Professor;
import br.com.alunoonline.api.repository.DisciplinaRepository;
import br.com.alunoonline.api.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisciplinaServiceImpl implements DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;
    private final DisciplinaMapper disciplinaMapper;
    private final ProfessorRepository professorRepository;


    @Override
    public DisciplinaResponseDTO criarDisciplina(DisciplinaRequestDTO dto) {
        Disciplina disciplina = disciplinaMapper.toEntity(dto);

        disciplina.setProfessor(buscarProfessor(dto.professorId()));
        disciplina.setExcluido(false);

        return disciplinaMapper.toDTO(disciplinaRepository.save(disciplina));
    }

    @Override
    public List<DisciplinaResponseDTO> listarDisciplinas() {
        return disciplinaRepository.findAll().stream()
                .map(disciplinaMapper::toDTO)
                .toList();
    }

    @Override
    public DisciplinaResponseDTO listarDisciplinaPorId(Long id) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Disciplina não encontrada"));

        return disciplinaMapper.toDTO(disciplina);
    }

    @Override
    public DisciplinaResponseDTO atualizarDisciplina(Long id, DisciplinaRequestDTO dto) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Disciplina não encontrada!"));

        disciplina.setNome(dto.nome());
        disciplina.setCargaHoraria(dto.cargaHoraria());
        disciplina.setDescricao(dto.descricao());
        disciplina.setProfessor(buscarProfessor(dto.professorId()));

        return disciplinaMapper.toDTO(disciplinaRepository.save(disciplina));
    }

    @Override
    public DisciplinaResponseDTO deletarDisciplina(Long id) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Disciplina não encontrada!"));

        disciplina.setExcluido(true);

        return disciplinaMapper.toDTO(disciplinaRepository.save(disciplina));
    }

    private Professor buscarProfessor(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Professor não encontrado"));
    }
}
