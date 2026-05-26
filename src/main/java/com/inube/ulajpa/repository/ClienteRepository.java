package com.inube.ulajpa.repository;

import com.inube.ulajpa.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, String> {
    List<ClienteModel> findByEstado(Integer estado);


    Optional<ClienteModel> findTopByNombreOrderByIdClienteDesc(String nombre);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO clientes (nombre, apellido,telefono,correo) " +
            "VALUES (:nombre, :apellido, :telefono, :correo)",nativeQuery = true)
    void insertarCliente(@Param("nombre") String nombre,
                         @Param("apellido") String apellido,
                         @Param("telefono") String telefono,
                         @Param("correo") String correo);
}
