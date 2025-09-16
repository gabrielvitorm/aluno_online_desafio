package br.com.alunoonline.api.repository;

import br.com.alunoonline.api.model.MatriculaCurso;
import br.com.alunoonline.api.model.MatriculaDisciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaDisciplinaRepository extends JpaRepository<MatriculaDisciplina, Long> {

    boolean existsByAlunoIdAndDisciplinaIdAndIdNot(Long alunoId, Long disciplinaId, Long id);
}
