package dev.couto.microsservice_user.Controller;

import dev.couto.microsservice_user.Dto.DadosToken;
import dev.couto.microsservice_user.Dto.UserLoginDto;
import dev.couto.microsservice_user.Dto.UsuarioRequestDto;
import dev.couto.microsservice_user.Service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class authController {

    private final UsuarioService usuarioService;


    @PostMapping("/login")
    public ResponseEntity<DadosToken> UserLogin(@RequestBody UserLoginDto dto){
        var token = usuarioService.login(dto);
        return ResponseEntity.ok(new DadosToken(token));
    }


    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@RequestBody UsuarioRequestDto dto){
        this.usuarioService.RegisterUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
