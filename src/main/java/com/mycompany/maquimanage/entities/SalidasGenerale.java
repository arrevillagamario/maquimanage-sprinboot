package com.mycompany.maquimanage.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "Salidas_Generales", schema = "dbo")
public class SalidasGenerale {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario idUsuario;

    @Column(name = "fecha_hora")
    private Instant fechaHora;

    @Column(name = "monto")
    private BigDecimal monto;

    @Lob
    @Column(name = "detalle")
    private String detalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria_salida")
    private CategoriasSalida idCategoriaSalida;

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

    public Instant getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Instant fechaHora) {
        this.fechaHora = fechaHora;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public CategoriasSalida getIdCategoriaSalida() {
        return idCategoriaSalida;
    }

    public void setIdCategoriaSalida(CategoriasSalida idCategoriaSalida) {
        this.idCategoriaSalida = idCategoriaSalida;
    }

}