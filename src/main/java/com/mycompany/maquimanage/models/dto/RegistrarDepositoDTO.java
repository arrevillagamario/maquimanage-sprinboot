package com.mycompany.maquimanage.models.dto;

import java.math.BigDecimal;

public class RegistrarDepositoDTO {
    private BigDecimal monto;
    private Integer idUsuario; // Asegúrate de incluir este campo

    // Getters y Setters
    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
