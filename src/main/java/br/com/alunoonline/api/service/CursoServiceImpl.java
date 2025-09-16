package br.com.alunoonline.api.service;

import br.com.alunoonline.api.dto.CursoRequestDTO;
import br.com.alunoonline.api.dto.CursoResponseDTO;
import br.com.alunoonline.api.mapper.CursoMapper;
import br.com.alunoonline.api.model.Curso;
import br.com.alunoonline.api.repository.CursoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService{

    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;

    @Override
    public CursoResponseDTO criarCurso(CursoRequestDTO dto) {
        Curso curso = cursoMapper.toEntity(dto);

        curso.setExcluido(false);

        return cursoMapper.toDTO(cursoRepository.save(curso));
    }

    @Override
    public List<CursoResponseDTO> listarCursos() {
        return cursoRepository.findAll().stream()
                .map(cursoMapper::toDTO)
                .toList();
    }

    @Override
    public CursoResponseDTO listarCursoPorId(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Curso repository"));

        return cursoMapper.toDTO(curso);
    }

    @Transactional
    @Override
    public CursoResponseDTO atualizarCurso(Long id, CursoRequestDTO dto) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Curso repository"));

        curso.setNome(dto.nome());
        curso.setTipoCurso(dto.tipoCurso());

        return cursoMapper.toDTO(cursoRepository.save(curso));
    }

    @Transactional
    @Override
    public CursoResponseDTO deletarCurso(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Curso repository"));

        curso.setExcluido(true);

        return cursoMapper.toDTO(cursoRepository.save(curso));
    }
}
