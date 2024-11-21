package com.mycompany.maquimanage.models.dto;

public class RegistrarPremioDTO {

    private Integer idMaquina;
    private Double montoBillete;
    private Double montoCoras;

    public Integer getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Integer idMaquina) {
        this.idMaquina = idMaquina;
    }

    public Double getMontoBillete() {
        return montoBillete;
    }

    public void setMontoBillete(Double montoBillete) {
        this.montoBillete = montoBillete;
    }

    public Double getMontoCoras() {
        return montoCoras;
    }

    public void setMontoCoras(Double montoCoras) {
        this.montoCoras = montoCoras;
    }
}
