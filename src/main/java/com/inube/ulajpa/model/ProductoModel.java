package com.inube.ulajpa.model;

import jakarta.persistence.*;

import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "productos")
@Data
public class ProductoModel {
    @Id
    @Column(name = "id_producto")
    private String idProducto;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio")
    private BigDecimal precio;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "estado")
    private Integer estado = 1;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private CategoriaModel categoria;
}
