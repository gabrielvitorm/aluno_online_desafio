package br.com.alunoonline.api.mapper;

import br.com.alunoonline.api.dto.MatriculaCursoRequestDTO;
import br.com.alunoonline.api.dto.MatriculaCursoResponseDTO;
import br.com.alunoonline.api.model.MatriculaCurso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MatriculaCursoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "aluno", ignore = true)
    @Mapping(target = "curso", ignore = true)
    MatriculaCurso toEntity(MatriculaCursoRequestDTO dto);

    @Mapping(target = "alunoId", source = "aluno.id")
    @Mapping(target = "alunoNome", source = "aluno.nome")
    @Mapping(target = "cursoId", source = "curso.id")
    @Mapping(target = "cursoNome", source = "curso.nome")
    MatriculaCursoResponseDTO toDTO(MatriculaCurso matriculaCurso);
}
