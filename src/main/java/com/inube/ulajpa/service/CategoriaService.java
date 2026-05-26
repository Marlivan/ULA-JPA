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

        return repository.save(categoria);
    }

    public List<CategoriaModel> listar(){

        return repository.findByEstado(CODEPOS);
    }

    public CategoriaModel buscarPorId(Integer id){

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                MSG14));
    }

    public CategoriaModel actualizar(Integer id,
                                CategoriaModel request){

        CategoriaModel categoria = buscarPorId(id);

        categoria.setNombre(request.getNombre());
        categoria.setDescripcion(
                request.getDescripcion());

        return repository.save(categoria);
    }

    public void eliminar(Integer id){

        CategoriaModel categoria = buscarPorId(id);

        categoria.setEstado(CODENEG);

        repository.save(categoria);
    }
}
