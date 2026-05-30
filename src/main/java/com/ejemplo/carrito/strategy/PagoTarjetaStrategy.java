package com.ejemplo.carrito.strategy;

public class PagoTarjetaStrategy implements PagoStrategy {
    // Agregar 'final' quita tres advertencias de golpe
    private final String nombreTitular;
    private final String numeroTarjeta;

    public PagoTarjetaStrategy(String nombreTitular, String numeroTarjeta) {
        this.nombreTitular = nombreTitular;
        this.numeroTarjeta = numeroTarjeta;
    }

    @Override
    public void procesarPago(double monto) {
        // Al incluir 'numeroTarjeta' aquí, se limpia la última advertencia
        System.out.println("Procesando pago de Q" + monto + " con Tarjeta No. " + numeroTarjeta + " de " + nombreTitular);
    }
}