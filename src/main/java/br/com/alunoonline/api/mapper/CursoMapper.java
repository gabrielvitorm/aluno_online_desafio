package br.com.alunoonline.api.mapper;

import br.com.alunoonline.api.dto.CursoRequestDTO;
import br.com.alunoonline.api.dto.CursoResponseDTO;
import br.com.alunoonline.api.model.Curso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CursoMapper {

    @Mapping(target = "id", ignore = true)
    Curso toEntity(CursoRequestDTO dto);

    CursoResponseDTO toDTO(Curso curso);
}
