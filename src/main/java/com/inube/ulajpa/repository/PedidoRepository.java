package com.inube.ulajpa.repository;

import com.inube.ulajpa.model.PedidoModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, String> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO pedidos (id_cliente, total, estado_pedido) " +
            "Values (:idCliente, :total, :estadoPedido)",nativeQuery = true)
    void insertarPedido(@Param("idCliente") String idCliente,
                        @Param("total") BigDecimal total,
                        @Param("estadoPedido") String estadoPedido);


    Optional<PedidoModel> findTopByClienteIdClienteOrderByIdPedidoDesc(String idCliente);
}
