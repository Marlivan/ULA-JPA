package com.inube.ulajpa.controller;

import com.inube.ulajpa.dto.ApiResponse;
import com.inube.ulajpa.dto.PedidoRequest;
import com.inube.ulajpa.service.PedidoService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.inube.ulajpa.util.UtilConstants.*;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {
    private final PedidoService service;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> generarPedido(
            @RequestBody PedidoRequest request){

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        MSG6,
                        service.generarPedido(request)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> cancelarPedido(
            @PathVariable String id){

        service.cancelarPedido(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        MSG7,
                        null
                )
        );
    }
}
