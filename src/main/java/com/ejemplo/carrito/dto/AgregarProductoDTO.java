package com.ejemplo.carrito.dto;

public class AgregarProductoDTO {
    private Long carritoId;
    private Long productoId;
    private Integer cantidad;

    public AgregarProductoDTO() {}

    public AgregarProductoDTO(Long carritoId, Long productoId, Integer cantidad) {
        this.carritoId = carritoId;
        this.productoId = productoId;
        this.cantidad = cantidad;
    }

    public Long getCarritoId() { return carritoId; }
    public Long getProductoId() { return productoId; }
    public Integer getCantidad() { return cantidad; }

    public void setCarritoId(Long carritoId) { this.carritoId = carritoId; }
    public void setProductoId(Long productoId) { this.productoId = productoId; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
}