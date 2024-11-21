package com.mycompany.maquimanage.repositories;

import com.mycompany.maquimanage.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RepositorioUsuarios extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsuario(String username);

    @Query("SELECT MAX(u.id) FROM Usuario u")
    Optional<Long> findMaxId();
    // Similar a ObtenerUusuarioPorUsername
}
