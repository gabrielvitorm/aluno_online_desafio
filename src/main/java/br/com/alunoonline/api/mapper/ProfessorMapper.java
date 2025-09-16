package br.com.alunoonline.api.mapper;

import br.com.alunoonline.api.dto.ProfessorRequestDTO;
import br.com.alunoonline.api.dto.ProfessorResponseDTO;
import br.com.alunoonline.api.model.Professor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {

    @Mapping(target = "id", ignore = true)
    Professor toEntity(ProfessorRequestDTO dto);


    @Mapping(target = "genero", source = "generoEnum")
    ProfessorResponseDTO toDTO(Professor professor);
}
