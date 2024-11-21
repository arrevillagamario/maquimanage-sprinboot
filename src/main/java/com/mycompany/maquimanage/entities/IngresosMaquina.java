package com.mycompany.maquimanage.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "Ingresos_Maquina", schema = "dbo")
public class IngresosMaquina {
    @Id
    @Column(name = "id_deposito", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario idUsuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_maquina")
    private Maquina idMaquina;

    @Column(name = "fecha_deposito")
    private Instant fechaDeposito;

    @Column(name = "monto_depositado")
    private BigDecimal montoDepositado;

    @Column(name = "monto_ganancia")
    private Long montoGanancia;

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

    public Maquina getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Maquina idMaquina) {
        this.idMaquina = idMaquina;
    }

    public Instant getFechaDeposito() {
        return fechaDeposito;
    }

    public void setFechaDeposito(Instant fechaDeposito) {
        this.fechaDeposito = fechaDeposito;
    }

    public BigDecimal getMontoDepositado() {
        return montoDepositado;
    }

    public void setMontoDepositado(BigDecimal montoDepositado) {
        this.montoDepositado = montoDepositado;
    }

    public Long getMontoGanancia() {
        return montoGanancia;
    }

    public void setMontoGanancia(Long montoGanancia) {
        this.montoGanancia = montoGanancia;
    }

}