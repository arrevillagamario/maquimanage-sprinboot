package com.mycompany.maquimanage.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "Depositos_Bancos", schema = "dbo")
public class DepositosBanco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configuración para autoincremento
    @Column(name = "id_deposito", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "id_usuario")
    private Usuario idUsuario;

    @Column(name = "fecha")
    private Instant fecha;

    @Column(name = "monto")
    private BigDecimal monto;

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Instant getFecha() {
        return fecha;
    }

    public void setFecha(Instant fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
}
