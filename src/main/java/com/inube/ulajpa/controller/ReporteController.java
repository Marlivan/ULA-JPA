package com.inube.ulajpa.controller;
import com.inube.ulajpa.dto.ApiResponse;
import com.inube.ulajpa.repository.DetallePedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.inube.ulajpa.util.UtilConstants.MSG1;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReporteController {
    private final DetallePedidoRepository repository;

    @GetMapping("/top-vendidos")
    public ResponseEntity<ApiResponse<?>> topVendidos(){

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        MSG1,
                        repository.topVendidos()
                )
        );
    }
}
