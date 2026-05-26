package com.inube.ulajpa.service;

import com.inube.ulajpa.model.ProductoModel;
import com.inube.ulajpa.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.inube.ulajpa.util.UtilConstants.*;

@Service
@RequiredArgsConstructor
public class ProductoService {
    private final ProductoRepository repository;

    public ProductoModel guardar(ProductoModel producto){

        repository.insertarProducto(
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getCategoria().getIdCategoria()
        );

        return repository.save(producto);
    }

    public List<ProductoModel> listar(){

        return repository.findByEstado(CODEPOS);
    }

    public ProductoModel buscarPorId(String id){

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                MSG16));
    }

    public ProductoModel actualizar(String id,
                               ProductoModel request){

        ProductoModel producto = buscarPorId(id);

        producto.setNombre(request.getNombre());
        producto.setDescripcion(
                request.getDescripcion());
        producto.setPrecio(request.getPrecio());
        producto.setStock(request.getStock());
        producto.setCategoria(
                request.getCategoria());

        return repository.save(producto);
    }

    public ProductoModel actualizarStock(String id,
                                    Integer stock){

        ProductoModel producto = buscarPorId(id);

        producto.setStock(stock);

        return repository.save(producto);
    }

    public void eliminar(String id){

        ProductoModel producto = buscarPorId(id);

        producto.setEstado(CODENEG);

        repository.save(producto);
    }
}
