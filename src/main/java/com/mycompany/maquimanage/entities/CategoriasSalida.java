package com.mycompany.maquimanage.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "Categorias_Salidas", schema = "dbo")
public class CategoriasSalida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria", nullable = false)
    private Long id;

    @Column(name = "descripcion", nullable = false, length = 100)
    private String descripcion;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
