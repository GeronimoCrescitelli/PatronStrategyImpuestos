package com.example.demo.strategy;

import org.springframework.stereotype.Component;

@Component("BRASIL")
public class ImpuestoBrasilStrategy implements ImpuestoStrategy {
    @Override
    public double calcularImpuesto(double monto) {
        // ICMS 17%
        return monto * 0.17;
    }

    @Override
    public String obtenerDescripcionImpuesto() {
        return "En Brasil el impuesto aplicado es del 17% (ICMS).";
    }
}
