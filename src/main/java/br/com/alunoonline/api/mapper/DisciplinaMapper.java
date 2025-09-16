package br.com.alunoonline.api.mapper;

import br.com.alunoonline.api.dto.DisciplinaRequestDTO;
import br.com.alunoonline.api.dto.DisciplinaResponseDTO;
import br.com.alunoonline.api.model.Disciplina;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DisciplinaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "professor", ignore = true)
    @Mapping(target = "curso", ignore = true)
    Disciplina toEntity(DisciplinaRequestDTO dto);

    @Mapping(target = "professorId", source = "professor.id")
    @Mapping(target = "professorNome", source = "professor.nome")
    @Mapping(target = "cursoId", source = "curso.id")
    @Mapping(target = "cursoNome", source = "curso.nome")
    DisciplinaResponseDTO toDTO(Disciplina disciplina);
}
