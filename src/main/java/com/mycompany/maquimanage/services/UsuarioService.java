package com.mycompany.maquimanage.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.mycompany.maquimanage.entities.Usuario;
import com.mycompany.maquimanage.models.dto.RegistroUsuarioDTO;
import com.mycompany.maquimanage.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean registrarUsuario(RegistroUsuarioDTO registroUsuarioDTO) {
        if (usuarioRepository.findByUsuario(registroUsuarioDTO.getUsuario()).isPresent()) {
            return false; // Usuario ya existe
        }

        // Encriptar la contraseña
        String hashedPassword = passwordEncoder.encode(registroUsuarioDTO.getContrasena());

        // Crear un nuevo usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(registroUsuarioDTO.getNombre());
        nuevoUsuario.setUsuario(registroUsuarioDTO.getUsuario());
        nuevoUsuario.setContrasena(hashedPassword);
        nuevoUsuario.setTipoUsuario(registroUsuarioDTO.getTipoUsuario());
        nuevoUsuario.setEstado(true); // Activo por defecto

        // Guardar en la base de datos
        usuarioRepository.save(nuevoUsuario);
        return true;
    }

}

