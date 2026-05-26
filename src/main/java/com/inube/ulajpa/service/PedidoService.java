package com.inube.ulajpa.service;

import com.inube.ulajpa.dto.PedidoRequest;
import com.inube.ulajpa.dto.ProductoPedidoDTO;
import com.inube.ulajpa.model.ClienteModel;
import com.inube.ulajpa.model.DetallePedidoModel;
import com.inube.ulajpa.model.PedidoModel;
import com.inube.ulajpa.model.ProductoModel;
import com.inube.ulajpa.repository.ClienteRepository;
import com.inube.ulajpa.repository.DetallePedidoRepository;
import com.inube.ulajpa.repository.PedidoRepository;
import com.inube.ulajpa.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static com.inube.ulajpa.util.UtilConstants.*;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;
    private final DetallePedidoRepository detalleRepository;

    @Transactional
    public PedidoModel generarPedido(PedidoRequest request){

        ClienteModel cliente =
                clienteRepository.findById(
                                request.getIdCliente())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        MSG15));

        PedidoModel pedido = new PedidoModel();

        pedido.setCliente(cliente);
        pedido.setEstadoPedido(CODE2);

        BigDecimal total = BigDecimal.ZERO;

        pedido = pedidoRepository.save(pedido);

        for(ProductoPedidoDTO item :
                request.getProductos()){

            ProductoModel producto =
                    productoRepository.findById(
                                    item.getIdProducto())
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            MSG16));

            if(producto.getStock() <
                    item.getCantidad()){

                throw new RuntimeException(
                        MSG17
                                + producto.getNombre());
            }

            BigDecimal subtotal =
                    producto.getPrecio().multiply(
                            BigDecimal.valueOf(
                                    item.getCantidad()));

            total = total.add(subtotal);

            DetallePedidoModel detalle =
                    new DetallePedidoModel();

            detalle.setPedido(pedido);
            detalle.setProducto(producto);
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecioUnitario(
                    producto.getPrecio());
            detalle.setSubtotal(subtotal);

            detalleRepository.save(detalle);

            producto.setStock(
                    producto.getStock()
                            - item.getCantidad());

            productoRepository.save(producto);
        }

        pedido.setTotal(total);

        return pedidoRepository.save(pedido);
    }

    @Transactional
    public void cancelarPedido(Integer idPedido){

        PedidoModel pedido =
                pedidoRepository.findById(idPedido)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        MSG18));

        pedido.setEstado(CODENEG);
        pedido.setEstadoPedido(CODE3);

        var detalles =
                detalleRepository.findByPedidoIdPedido(
                        idPedido);

        for(DetallePedidoModel detalle : detalles){

            ProductoModel producto =
                    detalle.getProducto();

            producto.setStock(
                    producto.getStock()
                            + detalle.getCantidad());

            productoRepository.save(producto);
        }

        pedidoRepository.save(pedido);
    }
}
