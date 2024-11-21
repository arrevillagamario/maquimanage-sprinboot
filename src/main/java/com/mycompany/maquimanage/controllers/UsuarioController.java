package com.mycompany.maquimanage.controllers;

import com.mycompany.maquimanage.models.dto.RegistroUsuarioDTO;
import com.mycompany.maquimanage.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegistroUsuarioDTO registroUsuarioDTO) {
        boolean registrado = usuarioService.registrarUsuario(registroUsuarioDTO);
        if (!registrado) {
            return ResponseEntity.badRequest().body("El usuario ya existe");
        }
        return ResponseEntity.ok("Usuario registrado exitosamente");
    }
}
