package com.inube.ulajpa.model;

import jakarta.persistence.*;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
@Data
public class PedidoModel {
    @Id
    @Column(name = "id_pedido")
    private String idPedido;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private ClienteModel cliente;

    @Column(name = "fecha_pedido")
    private LocalDateTime fechaPedido =
            LocalDateTime.now();

    private BigDecimal total;

    @Column(name = "estado_pedido")
    private String estadoPedido;

    @Column(name = "estado")
    private Integer estado = 1;

    @Column(name = "fecha_cancelacion")
    private LocalDateTime fechaCancelacion;
}
