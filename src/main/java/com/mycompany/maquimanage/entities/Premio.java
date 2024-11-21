package com.mycompany.maquimanage.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "Premios", schema = "dbo")
public class Premio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_premio", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_maquina")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Maquina idMaquina;

    @Column(name = "monto_billete")
    private BigDecimal montoBillete;

    @Column(name = "monto_coras")
    private BigDecimal montoCoras;

    @Column(name = "fecha_entrega")
    private Instant fechaEntrega;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "id_usuario_entrega")
    private Usuario idUsuarioEntrega;

    // Getters y Setters
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

    public Usuario getIdUsuarioEntrega() {
        return idUsuarioEntrega;
    }

    public void setIdUsuarioEntrega(Usuario idUsuarioEntrega) {
        this.idUsuarioEntrega = idUsuarioEntrega;
    }
}
