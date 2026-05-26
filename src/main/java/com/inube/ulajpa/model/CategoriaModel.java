package com.inube.ulajpa.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "categorias")
@Data
public class CategoriaModel {
    @Id
    @Column(name = "id_categoria")
    private String idCategoria;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado")
    private Integer estado = 1;
}
