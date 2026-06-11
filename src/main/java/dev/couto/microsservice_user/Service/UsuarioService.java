package dev.couto.microsservice_user.Service;

import dev.couto.microsservice_user.Dto.UsuarioRequestDto;
import dev.couto.microsservice_user.Dto.UsuarioResponseDto;
import dev.couto.microsservice_user.Mapper.MapperUsuario;
import dev.couto.microsservice_user.Repository.UsuarioRepository;
import dev.couto.microsservice_user.domin.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

    public UsuarioResponseDto atualizar(UsuarioRequestDto dto, UUID id){
       Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        var salve = usuarioRepository.save(usuario);
        return mapperUsuario.toDto(salve);


    }
    public Page<UsuarioResponseDto> listaUsuarios(Pageable pageable){
      return usuarioRepository.findAll(pageable)
              .map(mapperUsuario::toDto);

    }

    public UsuarioResponseDto buscarPorId(UUID id){
       Usuario usuario =   usuarioRepository.findById(id).orElseThrow(()-> new RuntimeException("usuario não encontrado"));
          return new UsuarioResponseDto(
                  usuario.getId(),
                  usuario.getNome(),
                  usuario.getEmail()
          );



    }

    public void deletar(UUID id){
        usuarioRepository.deleteById(id);
    }


    }


