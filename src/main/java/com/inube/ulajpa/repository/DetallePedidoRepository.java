package com.inube.ulajpa.repository;

import com.inube.ulajpa.dto.TopProductoDTO;
import com.inube.ulajpa.model.DetallePedidoModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedidoModel, Integer> {
    List<DetallePedidoModel> findByPedidoIdPedido(
            String idPedido);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO detalle_pedido (id_pedido, id_producto, cantidad, precio_unitario, subtotal) " +
            "VALUES(:idPedido, :idProducto, :cantidad, :precioUnitario, :subTotal)", nativeQuery = true)
    void insertarDetalle(
            @Param("idPedido") String idPedido,
            @Param("idProducto") String idProducto,
            @Param("cantidad") Integer cantidad,
            @Param("precioUnitario")BigDecimal precioUnitario,
            @Param("subtotal") BigDecimal subtotal);

    @Query("""
            SELECT new com.inube.ulajpa.dto.TopProductoDTO(
                d.producto.nombre,
                SUM(d.cantidad)
            )
            FROM DetallePedidoModel d
            WHERE d.estado = 1
            GROUP BY d.producto.nombre
            ORDER BY SUM(d.cantidad) DESC
            """)
    List<TopProductoDTO> topVendidos();
}
