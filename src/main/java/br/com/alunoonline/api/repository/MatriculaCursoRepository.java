package br.com.alunoonline.api.repository;

import br.com.alunoonline.api.model.MatriculaCurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaCursoRepository extends JpaRepository<MatriculaCurso, Long> {

    boolean existsByAlunoIdAndCursoId(Long alunoId, Long cursoId);
}
