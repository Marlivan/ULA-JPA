package com.inube.ulajpa.repository;

import com.inube.ulajpa.model.CategoriaModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaModel, String> {
    List<CategoriaModel> findByEstado(Integer estado);

    Optional<CategoriaModel> findTopByNombreOrderByIdCategoriaDesc(String nombre);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO categorias (nombre, descripcion, estado) " +
            "VALUES (:nombre, :descripcion, :estado)", nativeQuery = true)
    void insertarCategoria(@Param("nombre") String nombre,
                           @Param("descripcion")String descripcion,
                           @Param("estado")Integer estado);
}
