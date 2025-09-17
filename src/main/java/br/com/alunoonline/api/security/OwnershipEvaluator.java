package br.com.alunoonline.api.security;

import br.com.alunoonline.api.model.Disciplina;
import br.com.alunoonline.api.model.MatriculaCurso;
import br.com.alunoonline.api.model.MatriculaDisciplina;
import br.com.alunoonline.api.model.Usuario;
import br.com.alunoonline.api.repository.DisciplinaRepository;
import br.com.alunoonline.api.repository.MatriculaCursoRepository;
import br.com.alunoonline.api.repository.MatriculaDisciplinaRepository;
import br.com.alunoonline.api.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("ownershipEvaluator")
@RequiredArgsConstructor
public class OwnershipEvaluator {

    private final UsuarioRepository usuarioRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final MatriculaCursoRepository matriculaCursoRepository;
    private final MatriculaDisciplinaRepository matriculaDisciplinaRepository;

    private Optional<Usuario> findUsuario(Authentication auth) {
        if (auth == null || auth.getName() == null) return Optional.empty();
        return usuarioRepository.findByUsername(auth.getName());
    }

    @Transactional
    public boolean isAlunoDoUsuario(Long alunoId, Authentication authentication) {
        if (alunoId == null) return false;
        return findUsuario(authentication)
                .map(u -> u.getAluno() != null && alunoId.equals(u.getAluno().getId()))
                .orElse(false);
    }

    @Transactional
    public boolean isProfessorDaDisciplina(Long disciplinaId, Authentication authentication) {
        if (disciplinaId == null) return false;

        var usuarioOpt = findUsuario(authentication);
        if (usuarioOpt.isEmpty() || usuarioOpt.get().getProfessor() == null) return false;

        Long professorUsuarioId = usuarioOpt.get().getProfessor().getId();

        return disciplinaRepository.findById(disciplinaId)
                .map(Disciplina::getProfessor)
                .map(p -> p != null && professorUsuarioId.equals(p.getId()))
                .orElse(false);
    }

    @Transactional
    public boolean isProfessorDoUsuario(Long professorId, Authentication authentication) {
        if (professorId == null) return false;
        return findUsuario(authentication)
                .map(u -> u.getProfessor() != null && professorId.equals(u.getProfessor().getId()))
                .orElse(false);
    }

    @Transactional
    public boolean isMatriculaCursoDoUsuario(Long matriculaCursoId, Authentication authentication) {
        if (matriculaCursoId == null) return false;

        var usuarioOpt = findUsuario(authentication);
        if (usuarioOpt.isEmpty() || usuarioOpt.get().getAluno() == null) return false;

        Long alunoUsuarioId = usuarioOpt.get().getAluno().getId();

        return matriculaCursoRepository.findById(matriculaCursoId)
                .map(MatriculaCurso::getAluno)
                .map(a -> a != null && alunoUsuarioId.equals(a.getId()))
                .orElse(false);
    }

    @Transactional
    public boolean isMatriculaDisciplinaDoUsuario(Long matriculaDisciplinaId, Authentication authentication) {
        if (matriculaDisciplinaId == null) return false;

        var usuarioOpt = findUsuario(authentication);
        if (usuarioOpt.isEmpty() || usuarioOpt.get().getAluno() == null) return false;

        Long alunoUsuarioId = usuarioOpt.get().getAluno().getId();

        return matriculaDisciplinaRepository.findById(matriculaDisciplinaId)
                .map(MatriculaDisciplina::getAluno)
                .map(a -> a != null && alunoUsuarioId.equals(a.getId()))
                .orElse(false);
    }
}
