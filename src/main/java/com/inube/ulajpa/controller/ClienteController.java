package com.inube.ulajpa.controller;

import com.inube.ulajpa.dto.ApiResponse;
import com.inube.ulajpa.model.ClienteModel;
import com.inube.ulajpa.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.inube.ulajpa.util.UtilConstants.*;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService service;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> guardar(
            @RequestBody ClienteModel cliente){

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        MSG8,
                        service.guardar(cliente)
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
            @PathVariable Integer id){

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
            @PathVariable Integer id,
            @RequestBody ClienteModel cliente){

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        MSG9,
                        service.actualizar(id, cliente)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> eliminar(
            @PathVariable Integer id){

        service.eliminar(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        MSG10,
                        null
                )
        );
    }
}
