package dev.couto.microsservice_user.Dto;

import dev.couto.microsservice_user.domin.Usuario;

import java.util.UUID;

public record UsuarioResponseDto(
        UUID id,
        String nome,
        String email
) {

    public UsuarioResponseDto(Usuario model) {
     this(model.getId(),model.getNome(), model.getEmail());

    }
}
