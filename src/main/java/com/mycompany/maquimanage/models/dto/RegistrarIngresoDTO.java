package com.mycompany.maquimanage.models.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class RegistrarIngresoDTO {

    @NotNull(message = "El ID de la máquina es obligatorio.")
    private Integer idMaquina;

    @NotNull(message = "El monto depositado es obligatorio.")
    @DecimalMin(value = "0.01", message = "El monto depositado debe ser mayor que cero.")
    private Double montoDepositado;

    @DecimalMin(value = "0.01", message = "El monto retirado debe ser mayor que cero.")
    private Double montoRetirado;

    public Integer getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Integer idMaquina) {
        this.idMaquina = idMaquina;
    }

    public Double getMontoDepositado() {
        return montoDepositado;
    }

    public void setMontoDepositado(Double montoDepositado) {
        this.montoDepositado = montoDepositado;
    }

    public Double getMontoRetirado() {
        return montoRetirado;
    }

    public void setMontoRetirado(Double montoRetirado) {
        this.montoRetirado = montoRetirado;
    }
}
