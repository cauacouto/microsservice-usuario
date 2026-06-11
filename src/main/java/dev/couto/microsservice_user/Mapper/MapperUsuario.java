package dev.couto.microsservice_user.Mapper;


import dev.couto.microsservice_user.Dto.UsuarioRequestDto;
import dev.couto.microsservice_user.Dto.UsuarioResponseDto;
import dev.couto.microsservice_user.domin.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperUsuario {


     UsuarioResponseDto toDto(Usuario usuario);

    Usuario toEntity(UsuarioRequestDto dto);
}
