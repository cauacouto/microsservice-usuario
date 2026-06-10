package dev.couto.microsservice_user.Controller;

import dev.couto.microsservice_user.Dto.UsuarioRequestDto;
import dev.couto.microsservice_user.Dto.UsuarioResponseDto;
import dev.couto.microsservice_user.Service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @GetMapping
    public ResponseEntity<Page<UsuarioResponseDto>> listar(Pageable pageable){
        return ResponseEntity.ok(usuarioService.listaUsuarios(pageable));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> atualizar(UsuarioRequestDto dto, @PathVariable UUID id){
        var request = usuarioService.atualizar(dto,id);
        return ResponseEntity.ok().body(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable UUID id){
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }





}