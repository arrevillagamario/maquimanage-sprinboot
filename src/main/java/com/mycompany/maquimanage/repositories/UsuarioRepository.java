package com.mycompany.maquimanage.repositories;

import com.mycompany.maquimanage.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsuario(String usuario);

    @Query("SELECT MAX(u.id) FROM Usuario u")
    Optional<Long> findMaxId();
    // Cambiar usuario1 a usuario
}

