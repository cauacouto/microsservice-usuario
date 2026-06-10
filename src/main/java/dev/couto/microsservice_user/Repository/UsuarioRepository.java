package dev.couto.microsservice_user.Repository;


import dev.couto.microsservice_user.domin.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
}
