package com.ejemplo.carrito.dto;

public class OrdenPagoDTO {
    private Long carritoId;
    private String metodoPago;
    private String datosPago;

    public OrdenPagoDTO() {}

    public Long getCarritoId() { return carritoId; }
    public void setCarritoId(Long carritoId) { this.carritoId = carritoId; }

    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }

    public String getDatosPago() { return datosPago; }
    public void setDatosPago(String datosPago) { this.datosPago = datosPago; }
}
