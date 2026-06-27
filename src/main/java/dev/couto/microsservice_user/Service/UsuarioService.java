package dev.couto.microsservice_user.Service;

import dev.couto.microsservice_user.Dto.DadosToken;
import dev.couto.microsservice_user.Dto.UserLoginDto;
import dev.couto.microsservice_user.Dto.UsuarioRequestDto;
import dev.couto.microsservice_user.Dto.UsuarioResponseDto;
import dev.couto.microsservice_user.Mapper.MapperUsuario;
import dev.couto.microsservice_user.Repository.UsuarioRepository;
import dev.couto.microsservice_user.domin.Usuario;
import dev.couto.microsservice_user.infra.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService  {



    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;
    private final MapperUsuario mapperUsuario;
   private final PasswordEncoder passwordEncoder;


    public UsuarioResponseDto RegisterUsuario(UsuarioRequestDto dto){
        this.usuarioRepository.findByEmail(dto.email()).orElseThrow(() ->
                new RuntimeException("usuario já cadastrado"));
        Usuario usuario = mapperUsuario.toEntity(dto);
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setPassword(passwordEncoder.encode(dto.senha()));
        var salve = usuarioRepository.save(usuario);
        return mapperUsuario.toDto(salve);
    }


    public DadosToken login(UserLoginDto dto) {
        Usuario usuario = usuarioRepository.findByEmail(dto.email()).orElseThrow(() ->
                new RuntimeException("credencias não encotradas"));

        if (!passwordEncoder.matches(dto.senha(), usuario.getPassword())) {
            throw new RuntimeException("senha ivalida");
        }

            String token = tokenService.generateToken(usuario);
            return new DadosToken(token);
    }

    public UsuarioResponseDto atualizarParcialmente(UsuarioRequestDto dto, UUID id){
        Usuario usuario = usuarioRepository.findById(id).
                orElseThrow(()-> new RuntimeException("usuario não encontrado"));
        if (dto.nome() != null){
            usuario.setNome(dto.nome());
        }
        if (dto.email() != null){
            usuario.setEmail(dto.email());
        }
        var salve = usuarioRepository.save(usuario);
        return mapperUsuario.toDto(salve);


    }
    public Page<UsuarioResponseDto> listaUsuarios(Pageable pageable){
      return usuarioRepository.findAll(pageable)
              .map(mapperUsuario::toDto);

    }

    public UsuarioResponseDto buscarPorId(UUID id){
       Usuario usuario =   usuarioRepository.findById(id).orElseThrow(()-> new RuntimeException("usuario não encontrado"));
       return mapperUsuario.toDto(usuario);




    }

    public void deletar(UUID id){
        usuarioRepository.deleteById(id);
    }


    }


