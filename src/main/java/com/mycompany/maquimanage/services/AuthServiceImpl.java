package com.mycompany.maquimanage.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.mycompany.maquimanage.entities.Usuario;
import com.mycompany.maquimanage.models.dto.LoginDTO;
import com.mycompany.maquimanage.repositories.UsuarioRepository;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario authenticate(LoginDTO model) {
        // Buscar usuario en la base de datos
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsuario(model.getUsername());
        if (usuarioOpt.isEmpty()) {
            return null; // Usuario no encontrado
        }

        Usuario usuario = usuarioOpt.get();

        // Validar la contraseña
        if (passwordEncoder.matches(model.getPassword(), usuario.getContrasena())) {
            return usuario; // Credenciales válidas, devolver el objeto Usuario
        }

        return null; // Contraseña incorrecta
    }
}
