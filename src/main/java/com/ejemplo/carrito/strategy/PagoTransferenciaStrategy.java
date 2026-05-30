package com.ejemplo.carrito.strategy;

public class PagoTransferenciaStrategy implements PagoStrategy {
    // Agregar 'final' limpia la advertencia
    private final String numeroCuenta;

    public PagoTransferenciaStrategy(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    @Override
    public void procesarPago(double monto) {
        System.out.println("Procesando transferencia bancaria de Q" + monto + " desde la cuenta: " + numeroCuenta);
    }
}