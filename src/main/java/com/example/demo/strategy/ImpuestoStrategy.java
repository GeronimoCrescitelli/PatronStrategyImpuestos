package com.example.demo.strategy;

public interface ImpuestoStrategy {
    double calcularImpuesto(double monto);
    String obtenerDescripcionImpuesto();
}
