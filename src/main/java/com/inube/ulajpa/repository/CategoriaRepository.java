package com.inube.ulajpa.repository;

import com.inube.ulajpa.model.CategoriaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaModel, Integer> {
    List<CategoriaModel> findByEstado(Integer estado);
}
