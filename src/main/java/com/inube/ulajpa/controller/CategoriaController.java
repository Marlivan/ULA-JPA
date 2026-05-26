package com.inube.ulajpa.controller;

import com.inube.ulajpa.dto.ApiResponse;
import com.inube.ulajpa.model.CategoriaModel;
import com.inube.ulajpa.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.inube.ulajpa.util.UtilConstants.*;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaService service;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> guardar(
            @RequestBody CategoriaModel categoria){

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        MSG11,
                        service.guardar(categoria)
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> listar(){

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        MSG1,
                        service.listar()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> buscarPorId(
            @PathVariable String id){

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        MSG1,
                        service.buscarPorId(id)
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> actualizar(
            @PathVariable String id,
            @RequestBody CategoriaModel categoria){

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        MSG12,
                        service.actualizar(id, categoria)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> eliminar(
            @PathVariable String id){

        service.eliminar(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        MSG13,
                        null
                )
        );
    }
}
