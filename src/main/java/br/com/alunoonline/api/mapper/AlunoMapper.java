package br.com.alunoonline.api.mapper;

import br.com.alunoonline.api.dto.AlunoRequestDTO;
import br.com.alunoonline.api.dto.AlunoResponseDTO;
import br.com.alunoonline.api.model.Aluno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AlunoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "endereco", ignore = true)
    @Mapping(target = "curso", ignore = true)
    @Mapping(target = "excluido", ignore = true)
    @Mapping(target = "genero", source = "genero")
    Aluno toEntity(AlunoRequestDTO dto);

    @Mapping(target = "genero", source = "genero")
    @Mapping(target = "cursoId", source = "curso.id")
    @Mapping(target = "cursoNome", source = "curso.nome")
    @Mapping(target = "endereco", source = "endereco")
    AlunoResponseDTO toDTO(Aluno aluno);
}
