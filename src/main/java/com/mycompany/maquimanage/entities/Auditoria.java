package com.mycompany.maquimanage.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Auditoria", schema = "dbo")
public class Auditoria {
    @Id
    @Column(name = "id_movimiento", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario idUsuario;

    @Lob
    @Column(name = "accion")
    private String accion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

}