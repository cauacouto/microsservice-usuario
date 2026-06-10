package dev.couto.microsservice_user.Service;

import dev.couto.microsservice_user.Dto.UsuarioRequestDto;
import dev.couto.microsservice_user.Dto.UsuarioResponseDto;
import dev.couto.microsservice_user.Mapper.MapperUsuario;
import dev.couto.microsservice_user.Repository.UsuarioRepository;
import dev.couto.microsservice_user.domin.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {



    private final UsuarioRepository usuarioRepository;
    private final MapperUsuario mapperUsuario;


    public UsuarioResponseDto salvarUsuario(UsuarioRequestDto dto){
        Usuario usuario = mapperUsuario.toEntity(dto);
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        var salve = usuarioRepository.save(usuario);
        return mapperUsuario.toDto(salve);
    }

}
