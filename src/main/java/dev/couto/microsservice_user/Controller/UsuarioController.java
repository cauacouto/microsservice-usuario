package dev.couto.microsservice_user.Controller;

import dev.couto.microsservice_user.Dto.UsuarioRequestDto;
import dev.couto.microsservice_user.Dto.UsuarioResponseDto;
import dev.couto.microsservice_user.Service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;


    @PostMapping
    public ResponseEntity<UsuarioResponseDto> criarUsuario(@RequestBody UsuarioRequestDto dto) {
        var request = usuarioService.salvarUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(request);
    }
}