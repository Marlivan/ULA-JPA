package com.inube.ulajpa.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "clientes")
@Data
public class ClienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "seq_clientes")

    @SequenceGenerator(
            name = "seq_clientes",
            sequenceName = "seq_clientes",
            allocationSize = 1
    )
    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "correo")
    private String correo;

    @Column(name = "estado")
    private Integer estado = 1;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro =
            LocalDateTime.now();
}

