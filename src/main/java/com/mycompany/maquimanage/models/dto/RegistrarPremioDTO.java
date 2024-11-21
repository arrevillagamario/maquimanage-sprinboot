package com.mycompany.maquimanage.models.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class RegistrarPremioDTO {

    @NotNull(message = "El ID de la máquina es obligatorio.")
    private Integer idMaquina;

    private BigDecimal montoBillete;

    private BigDecimal montoCoras;

    @NotNull(message = "El ID del usuario es obligatorio.")
    private Integer idUsuario;

    // Getters y setters
    public Integer getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Integer idMaquina) {
        this.idMaquina = idMaquina;
    }

    public BigDecimal getMontoBillete() {
        return montoBillete;
    }

    public void setMontoBillete(BigDecimal montoBillete) {
        this.montoBillete = montoBillete;
    }

    public BigDecimal getMontoCoras() {
        return montoCoras;
    }

    public void setMontoCoras(BigDecimal montoCoras) {
        this.montoCoras = montoCoras;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
