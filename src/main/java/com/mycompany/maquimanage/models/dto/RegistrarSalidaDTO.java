package com.mycompany.maquimanage.models.dto;

import com.mycompany.maquimanage.entities.CategoriasSalida;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;



public class RegistrarSalidaDTO {
    private String detalle;
    private BigDecimal monto;
    private Long idCategoriaSalida;
    private Long idUsuario; // Nuevo campo

    // Getters y Setters
    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Long getIdCategoriaSalida() {
        return idCategoriaSalida;
    }

    public void setIdCategoriaSalida(Long idCategoriaSalida) {
        this.idCategoriaSalida = idCategoriaSalida;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
}

