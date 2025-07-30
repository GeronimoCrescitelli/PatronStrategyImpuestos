package com.example.demo.dto;

import com.example.demo.model.Pais;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FacturaResponseDTO {
    private Long id;
    private String cliente;
    private double monto;
    private Pais pais;
    private double impuesto;
    private double total;
}