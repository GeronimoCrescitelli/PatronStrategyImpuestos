package com.example.demo.controller;

import com.example.demo.dto.FacturaRequestDTO;
import com.example.demo.dto.FacturaResponseDTO;
import com.example.demo.model.Pais;
import com.example.demo.service.FacturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
@RequiredArgsConstructor
public class FacturaController {

    private final FacturaService facturaService;

    @PostMapping
    public ResponseEntity<FacturaResponseDTO> createFactura(@RequestBody FacturaRequestDTO dto) {
        FacturaResponseDTO response = facturaService.createFactura(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<FacturaResponseDTO>> obtenerTodas() {
        return ResponseEntity.ok(facturaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(facturaService.obtenerPorId(id));
    }

    @GetMapping("/pais/{pais}")
    public ResponseEntity<List<FacturaResponseDTO>> obtenerPorPais(@PathVariable Pais pais) {
        return ResponseEntity.ok(facturaService.obtenerPorPais(pais));
    }

    @GetMapping("/impuesto/{pais}")
    public ResponseEntity<String> obtenerImpuestoPorPais(@PathVariable Pais pais) {
        return ResponseEntity.ok(facturaService.obtenerImpuestoPorPais(pais));
    }
}