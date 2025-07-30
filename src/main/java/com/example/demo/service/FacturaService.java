package com.example.demo.service;


import com.example.demo.dto.FacturaRequestDTO;
import com.example.demo.dto.FacturaResponseDTO;
import com.example.demo.model.Factura;
import com.example.demo.model.Pais;
import com.example.demo.repository.FacturaRepository;
import com.example.demo.strategy.ImpuestoStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FacturaService {

    private final FacturaRepository facturaRepositorio;
    private final Map<String, ImpuestoStrategy> mapaStrategy; // Inyectado por Spring

    public FacturaResponseDTO createFactura(FacturaRequestDTO dto) {
        ImpuestoStrategy estrategia = mapaStrategy.get(dto.getPais().name());
        if (estrategia == null) {
            throw new IllegalArgumentException("País no soportado para el cálculo de impuestos");
        }
        double impuesto = estrategia.calcularImpuesto(dto.getMonto());
        double total = dto.getMonto() + impuesto;

        Factura factura = Factura.builder()
                .cliente(dto.getCliente())
                .monto(dto.getMonto())
                .pais(dto.getPais())
                .impuesto(impuesto)
                .total(total)
                .build();

        Factura guardada = facturaRepositorio.save(factura);

        return aDTO(guardada);
    }

    public List<FacturaResponseDTO> obtenerTodas() {
        return facturaRepositorio.findAll()
                .stream()
                .map(this::aDTO)
                .collect(Collectors.toList());
    }

    public FacturaResponseDTO obtenerPorId(Long id) {
        Factura factura = facturaRepositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Factura no encontrada"));
        return aDTO(factura);
    }

    public List<FacturaResponseDTO> obtenerPorPais(Pais pais) {
        return facturaRepositorio.findAll()
                .stream()
                .filter(f -> f.getPais() == pais)
                .map(this::aDTO)
                .collect(Collectors.toList());
    }
    public String obtenerImpuestoPorPais(Pais pais) {
        ImpuestoStrategy estrategia = mapaStrategy.get(pais.name());
        if (estrategia == null) {
            return "País no soportado para el cálculo de impuestos.";
        }
        return estrategia.obtenerDescripcionImpuesto();
    }

    private FacturaResponseDTO aDTO(Factura factura) {
        return FacturaResponseDTO.builder()
                .id(factura.getId())
                .cliente(factura.getCliente())
                .monto(factura.getMonto())
                .pais(factura.getPais())
                .impuesto(factura.getImpuesto())
                .total(factura.getTotal())
                .build();
    }
}