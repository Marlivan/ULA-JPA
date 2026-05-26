package com.inube.ulajpa.model;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "detalle_pedido")
@Data
public class DetallePedidoModel {
    @Id
    @Column(name = "id_detalle")
    private String idDetalle;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private PedidoModel pedido;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private ProductoModel producto;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;

    @Column(name = "subtotal")
    private BigDecimal subtotal;

    @Column(name = "estado")
    private Integer estado = 1;
}
