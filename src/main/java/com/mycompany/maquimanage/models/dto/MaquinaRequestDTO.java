package com.mycompany.maquimanage.models.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class MaquinaRequestDTO {

    @NotNull(message = "El estado de la máquina no puede ser nulo.")
    private Boolean estado;

    @NotBlank(message = "El nombre de la máquina no puede estar vacío.")
    private String maquina;

    @NotNull(message = "Es necesario agregar un depósito para registrar la máquina.")
    @DecimalMin(value = "0.0", inclusive = true, message = "El depósito debe ser mayor o igual a cero.")
    private BigDecimal depsoitos;

    // Getters y Setters
    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getMaquina() {
        return maquina;
    }

    public void setMaquina(String maquina) {
        this.maquina = maquina;
    }

    public BigDecimal getDepositos() {
        return depsoitos;
    }

    public void setDepositos(BigDecimal depositos) {
        this.depsoitos = depositos;
    }
}
