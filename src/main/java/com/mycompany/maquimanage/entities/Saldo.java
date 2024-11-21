package com.mycompany.maquimanage.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Saldos", schema = "dbo")
public class Saldo {
    @Id
    @Column(name = "id_saldo", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "mes")
    private String mes;

    @Lob
    @Column(name = "anio")
    private String anio;

    @Column(name = "saldo_inicial")
    private BigDecimal saldoInicial;

    @Column(name = "saldo_final")
    private BigDecimal saldoFinal;

    @Column(name = "ganancias_perdidas")
    private BigDecimal gananciasPerdidas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario idUsuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_maquina")
    private Maquina idMaquina;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public BigDecimal getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(BigDecimal saldoFinal) {
        this.saldoFinal = saldoFinal;
    }

    public BigDecimal getGananciasPerdidas() {
        return gananciasPerdidas;
    }

    public void setGananciasPerdidas(BigDecimal gananciasPerdidas) {
        this.gananciasPerdidas = gananciasPerdidas;
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

}