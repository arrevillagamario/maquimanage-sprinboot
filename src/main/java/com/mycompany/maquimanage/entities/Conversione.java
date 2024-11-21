package com.mycompany.maquimanage.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Conversiones", schema = "dbo")
public class Conversione {
    @Id
    @Column(name = "id_convercion", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario idUsuario;

    @Column(name = "monto_billete")
    private BigDecimal montoBillete;

    @Column(name = "monto_cora")
    private Integer montoCora;

    @Column(name = "tipo_transacion")
    private Character tipoTransacion;

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

    public BigDecimal getMontoBillete() {
        return montoBillete;
    }

    public void setMontoBillete(BigDecimal montoBillete) {
        this.montoBillete = montoBillete;
    }

    public Integer getMontoCora() {
        return montoCora;
    }

    public void setMontoCora(Integer montoCora) {
        this.montoCora = montoCora;
    }

    public Character getTipoTransacion() {
        return tipoTransacion;
    }

    public void setTipoTransacion(Character tipoTransacion) {
        this.tipoTransacion = tipoTransacion;
    }

}