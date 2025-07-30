package com.example.demo.dto;

import com.example.demo.model.Pais;
import lombok.Data;

@Data
public class FacturaRequestDTO {
    private String cliente;
    private double monto;
    private Pais pais;
}