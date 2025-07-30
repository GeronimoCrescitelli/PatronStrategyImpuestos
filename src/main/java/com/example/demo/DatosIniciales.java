package com.example.demo;

import com.example.demo.dto.FacturaRequestDTO;
import com.example.demo.model.Pais;
import com.example.demo.service.FacturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatosIniciales implements CommandLineRunner {

    private final FacturaService facturaServicio;

    @Override
    public void run(String... args) {
        facturaServicio.createFactura(crear("Juan Pérez", 1000.0, Pais.ARGENTINA));
        facturaServicio.createFactura(crear("Maria Silva", 2000.0, Pais.BRASIL));
        facturaServicio.createFactura(crear("John Smith", 1500.0, Pais.USA));
        facturaServicio.createFactura(crear("Joao Sousa", 1700, Pais.BRASIL));
        facturaServicio.createFactura(crear("Ana Gomez", 30000.0, Pais.ARGENTINA));
        facturaServicio.createFactura(crear("Paul Johnson", 2500.0, Pais.USA));
    }

    private FacturaRequestDTO crear(String cliente, double monto, Pais pais) {
        FacturaRequestDTO dto = new FacturaRequestDTO();
        dto.setCliente(cliente);
        dto.setMonto(monto);
        dto.setPais(pais);
        return dto;
    }
}