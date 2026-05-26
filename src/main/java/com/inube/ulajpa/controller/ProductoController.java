package com.inube.ulajpa.controller;

import com.inube.ulajpa.dto.ApiResponse;
import com.inube.ulajpa.model.ProductoModel;
import com.inube.ulajpa.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

import static com.inube.ulajpa.util.UtilConstants.*;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {
    private final ProductoService service;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> guardar(
            @RequestBody ProductoModel producto){

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        MSG2,
                        service.guardar(producto)
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
            @RequestBody ProductoModel producto){

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        MSG3,
                        service.actualizar(id, producto)
                )
        );
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<ApiResponse<?>> actualizarStock(
            @PathVariable String id,
            @RequestBody Map<String, Integer> request){

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        MSG4,
                        service.actualizarStock(
                                id,
                                request.get(CODE1))
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
                        MSG5,
                        null
                )
        );
    }
}
