package br.com.alunoonline.api.mapper;

import br.com.alunoonline.api.dto.HistoricoAlunoResponseDTO;
import br.com.alunoonline.api.dto.MatriculaDisciplinaRequestDTO;
import br.com.alunoonline.api.dto.MatriculaDisciplinaResponseDTO;
import br.com.alunoonline.api.model.MatriculaDisciplina;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MatriculaDisciplinaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "aluno", ignore = true)
    @Mapping(target = "disciplina", ignore = true)
    @Mapping(target = "matriculaCurso", ignore = true)
    MatriculaDisciplina toEntity(MatriculaDisciplinaRequestDTO dto);

    @Mapping(target = "alunoId", source = "aluno.id")
    @Mapping(target = "alunoNome", source = "aluno.nome")
    @Mapping(target = "disciplinaId", source = "disciplina.id")
    @Mapping(target = "disciplinaNome", source = "disciplina.nome")
    @Mapping(target = "matriculaCursoId", source = "matriculaCurso.id")
    @Mapping(target = "matriculaCursoNome", source = "matriculaCurso.curso.nome")
    MatriculaDisciplinaResponseDTO toDTO(MatriculaDisciplina md);

    @Mapping(target = "alunoId", source = "matriculaDisciplina.aluno.id")
    @Mapping(target = "alunoNome", source = "matriculaDisciplina.aluno.nome")
    @Mapping(target = "disciplinaId", source = "matriculaDisciplina.disciplina.id")
    @Mapping(target = "disciplinaNome", source = "matriculaDisciplina.disciplina.nome")
    @Mapping(target = "nota1", source = "matriculaDisciplina.nota1")
    @Mapping(target = "nota2", source = "matriculaDisciplina.nota2")
    @Mapping(target = "media", source = "media")
    @Mapping(target = "matriculaStatus", source = "matriculaDisciplina.matriculaStatus")
    HistoricoAlunoResponseDTO toHistoricoDTO(MatriculaDisciplina matriculaDisciplina, double media);
}
