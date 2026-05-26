package com.inube.ulajpa.service;

import com.inube.ulajpa.model.CategoriaModel;
import com.inube.ulajpa.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.inube.ulajpa.util.UtilConstants.*;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository repository;

    public CategoriaModel guardar(CategoriaModel categoria){

        repository.insertarCategoria(
                categoria.getNombre(),
                categoria.getDescripcion(),
                categoria.getEstado()
        );
        return repository.findTopByNombreOrderByIdCategoriaDesc(categoria.getNombre())
                .orElseThrow(() -> new RuntimeException("Error al guardar categoria"));

    }

    public List<CategoriaModel> listar(){

        return repository.findByEstado(CODEPOS);
    }

    public CategoriaModel buscarPorId(String id){

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                MSG14));
    }

    public CategoriaModel actualizar(String id,
                                CategoriaModel request){

        CategoriaModel categoria = buscarPorId(id);

        categoria.setNombre(request.getNombre());
        categoria.setDescripcion(
                request.getDescripcion());

        return repository.save(categoria);
    }

    public void eliminar(String id){

        CategoriaModel categoria = buscarPorId(id);

        categoria.setEstado(CODENEG);

        repository.save(categoria);
    }
}
