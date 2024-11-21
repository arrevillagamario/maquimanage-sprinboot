package com.mycompany.maquimanage.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Maquinas", schema = "dbo")
public class Maquina {
    @Id
    @Column(name = "id_maquina", nullable = false)
    private Integer id;

    @Column(name = "estado")
    private Boolean estado;

    @Lob
    @Column(name = "maquina")
    private String maquina;

    @Column(name = "depsoitos")
    private BigDecimal depsoitos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public BigDecimal getDepsoitos() {
        return depsoitos;
    }

    public void setDepsoitos(BigDecimal depsoitos) {
        this.depsoitos = depsoitos;
    }

}