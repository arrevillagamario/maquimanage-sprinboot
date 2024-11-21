package com.mycompany.maquimanage.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Maquinas", schema = "dbo")
public class Maquina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del ID
    @Column(name = "id_maquina", nullable = false)
    private Integer id;

    @Column(name = "estado", nullable = false) // Estado no debe ser nulo
    private Boolean estado;

    @Column(name = "maquina", length = 100) // Campo con longitud limitada
    private String maquina;

    @Column(name = "depsoitos", precision = 10, scale = 2) // Corregido y configurado para valores decimales
    private BigDecimal depsoitos;

    // Getters y Setters
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

    public BigDecimal getDepositos() {
        return depsoitos;
    }

    public void setDepositos(BigDecimal depositos) {
        this.depsoitos = depositos;
    }
}
