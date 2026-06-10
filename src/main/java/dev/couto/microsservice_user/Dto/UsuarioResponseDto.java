package dev.couto.microsservice_user.Dto;

import java.util.UUID;

public record UsuarioResponseDto(
        UUID id,
        String nome,
        String email
) {
}
