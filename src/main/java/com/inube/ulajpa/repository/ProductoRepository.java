package com.inube.ulajpa.repository;

import com.inube.ulajpa.model.ClienteModel;
import com.inube.ulajpa.model.ProductoModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoModel, String> {
    List<ProductoModel> findByEstado(Integer estado);

    Optional<ProductoModel> findTopByNombreOrderByIdProductoDesc(String nombre);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO productos (nombre, descripcion,precio,stock,idCategoria) " +
            "VALUES (:nombre, :descripcion, :precio, :stock, :idCategoria)",nativeQuery = true)
    void insertarProducto(@Param("nombre") String nombre,
                         @Param("descripcion") String descripcion,
                         @Param("precio") BigDecimal precio,
                         @Param("stock") Integer stock,
                         @Param("idCategoria") String idCategoria);
}
