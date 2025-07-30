package com.example.demo.strategy;

import org.springframework.stereotype.Component;

    @Component("USA")
public class ImpuestoUsaStrategy implements ImpuestoStrategy {
    @Override
    public double calcularImpuesto(double monto) {
        // Sales Tax 8%
        return monto * 0.08;
    }

        @Override
        public String obtenerDescripcionImpuesto() {
            return "En Estados Unidos el impuesto aplicado es del 8% (Sales Tax).";
        }
}
