package com.example.demo.strategy;

import org.springframework.stereotype.Component;

@Component("ARGENTINA")
public class ImpuestoArgentinaStrategy implements ImpuestoStrategy{
    @Override
    public double calcularImpuesto(double monto) {
        // IVA 21%
        return monto * 0.21;
    }
    @Override
    public String obtenerDescripcionImpuesto() {
        return "En Argentina el impuesto aplicado es del 21% (IVA).";
    }
}
