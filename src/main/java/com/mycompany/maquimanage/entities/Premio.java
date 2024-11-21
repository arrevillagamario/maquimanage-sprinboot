package com.mycompany.maquimanage.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "Premios", schema = "dbo")
public class Premio {
    @Id
    @Column(name = "id_premio", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_maquina")
    private Maquina idMaquina;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_entrega")
    private Usuario idUsuarioEntrega;

    @Column(name = "monto_billete")
    private BigDecimal montoBillete;

    @Column(name = "monto_coras")
    private BigDecimal montoCoras;

    @Column(name = "fecha_entrega")
    private Instant fechaEntrega;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Maquina getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Maquina idMaquina) {
        this.idMaquina = idMaquina;
    }

    public Usuario getIdUsuarioEntrega() {
        return idUsuarioEntrega;
    }

    public void setIdUsuarioEntrega(Usuario idUsuarioEntrega) {
        this.idUsuarioEntrega = idUsuarioEntrega;
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

    public Instant getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Instant fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

}