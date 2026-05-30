package com.ejemplo.carrito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.carrito.dto.AgregarProductoDTO;
import com.ejemplo.carrito.dto.OrdenPagoDTO;
import com.ejemplo.carrito.model.Carrito;
import com.ejemplo.carrito.service.CarritoService;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    @Autowired 
    private CarritoService service;

    @PostMapping("/crear")
    public Carrito crear() {
        return service.crearCarrito();
    }

    @PostMapping("/agregar")
    public String agregar(@RequestBody AgregarProductoDTO dto) {
        service.agregarProducto(dto.getCarritoId(), dto.getProductoId(), dto.getCantidad());
        return "Producto agregado!";
    }

    @PostMapping("/procesar")
    public ResponseEntity<String> procesarOrden(@RequestBody OrdenPagoDTO dto) {
        try {
            String resultado = service.procesarOrdenYPago(dto);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al procesar la orden: " + e.getMessage());
        }
    } 
}
