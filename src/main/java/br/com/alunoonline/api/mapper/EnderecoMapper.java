package br.com.alunoonline.api.mapper;

import br.com.alunoonline.api.dto.EnderecoDTO;
import br.com.alunoonline.api.model.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    @Mapping(target = "id", ignore = true)
    Endereco toEntity(EnderecoDTO dto);

    EnderecoDTO toDTO(Endereco endereco);
}
