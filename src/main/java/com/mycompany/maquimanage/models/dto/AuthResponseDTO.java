package com.mycompany.maquimanage.models.dto;

import java.time.LocalDateTime;

public class AuthResponseDTO {
    private String tipoUsuario;
    private String token;
    private LocalDateTime expiration;

    public AuthResponseDTO(String tipoUsuario, String token, LocalDateTime expiration) {
        this.tipoUsuario = tipoUsuario;
        this.token = token;
        this.expiration = expiration;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDateTime expiration) {
        this.expiration = expiration;
    }
}
