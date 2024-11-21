package com.mycompany.maquimanage.controllers;

import com.mycompany.maquimanage.entities.Usuario;
import com.mycompany.maquimanage.models.dto.LoginDTO;
import com.mycompany.maquimanage.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated @RequestBody LoginDTO loginDTO) {
        Usuario usuario = authService.authenticate(loginDTO);
        if (usuario != null) {
            return ResponseEntity.ok(usuario); // Devolver el objeto usuario en caso de éxito
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas"); // En caso de error
    }
}
