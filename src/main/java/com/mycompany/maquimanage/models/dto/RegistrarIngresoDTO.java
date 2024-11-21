package com.mycompany.maquimanage.models.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class RegistrarIngresoDTO {

    @NotNull(message = "El ID de la máquina es obligatorio.")
    private Integer idMaquina;

    @NotNull(message = "El monto depositado es obligatorio.")
    @DecimalMin(value = "0.01", message = "El monto depositado debe ser mayor que cero.")
    private BigDecimal montoDepositado;

    @DecimalMin(value = "0.01", message = "El monto retirado debe ser mayor que cero.")
    private BigDecimal montoRetirado;

    @NotNull(message = "El ID del usuario es obligatorio.")
    private Integer idUsuario;

    public Integer getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Integer idMaquina) {
        this.idMaquina = idMaquina;
    }

    public BigDecimal getMontoDepositado() {
        return montoDepositado;
    }

    public void setMontoDepositado(BigDecimal montoDepositado) {
        this.montoDepositado = montoDepositado;
    }

    public BigDecimal getMontoRetirado() {
        return montoRetirado;
    }

    public void setMontoRetirado(BigDecimal montoRetirado) {
        this.montoRetirado = montoRetirado;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
