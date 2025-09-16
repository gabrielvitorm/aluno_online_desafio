package br.com.alunoonline.api.mapper;

import br.com.alunoonline.api.dto.HistoricoAlunoResponseDTO;
import br.com.alunoonline.api.dto.MatriculaCursoResponseDTO;
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
    MatriculaDisciplina toEntity(MatriculaDisciplinaRequestDTO dto);

    @Mapping(target = "alunoId", source = "aluno.id")
    @Mapping(target = "alunoNome", source = "aluno.nome")
    @Mapping(target = "disciplinaId", source = "disciplina.id")
    @Mapping(target = "disciplinaNome", source = "disciplina.nome")
    @Mapping(target = "status", source = "status")
    MatriculaDisciplinaResponseDTO toDTO(MatriculaDisciplina matriculaDisciplina);

//    @Mapping(target = "alunoId", source = "matriculaAluno.aluno.id")
//    @Mapping(target = "alunoNome", source = "matriculaAluno.aluno.nome")
//    @Mapping(target = "disciplinaId", source = "matriculaAluno.disciplina.id")
//    @Mapping(target = "disciplinaNome", source = "matriculaAluno.disciplina.nome")
//    @Mapping(target = "nota1", source = "matriculaAluno.nota1")
//    @Mapping(target = "nota2", source = "matriculaAluno.nota2")
//    @Mapping(target = "media", source = "media")
//    @Mapping(target = "status", source = "matriculaAluno.status")
//    HistoricoAlunoResponseDTO toHistoricoDTO(MatriculaDisciplina matriculaDisciplina, double media);
}
