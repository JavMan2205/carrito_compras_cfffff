package com.ejemplo.carrito.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejemplo.carrito.dto.OrdenPagoDTO;
import com.ejemplo.carrito.model.Carrito;
import com.ejemplo.carrito.model.ItemCarrito;
import com.ejemplo.carrito.model.Producto;
import com.ejemplo.carrito.repository.CarritoRepository;
import com.ejemplo.carrito.repository.ProductoRepository;
import com.ejemplo.carrito.strategy.PagoStrategy;
import com.ejemplo.carrito.strategy.PagoTarjetaStrategy;
import com.ejemplo.carrito.strategy.PagoTransferenciaStrategy;

@Service
public class CarritoService {

    @Autowired 
    private CarritoRepository carritoRepo;
    
    @Autowired 
    private ProductoRepository productoRepo;

    public Carrito crearCarrito() {
        Carrito c = new Carrito();
        c.setFechaCreacion(LocalDateTime.now());
        return carritoRepo.save(c);
    }

    @Transactional
    public void agregarProducto(Long cId, Long pId, Integer cant) {
        Carrito carrito = carritoRepo.findById(cId).orElseThrow();
        Producto prod = productoRepo.findById(pId).orElseThrow();

        if (prod.getStock() >= cant) {
            ItemCarrito item = new ItemCarrito();
            item.setCarrito(carrito);
            item.setProducto(prod);
            item.setCantidad(cant);
            item.setPrecioUnitario(prod.getPrecio());
            
            carrito.getItems().add(item);
            prod.setStock(prod.getStock() - cant);
            
            productoRepo.save(prod);
            carritoRepo.save(carrito);
        }
    }

    @Transactional
    public String procesarOrdenYPago(OrdenPagoDTO dto) {
        Carrito carrito = carritoRepo.findById(dto.getCarritoId())
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        if (carrito.getItems().isEmpty()) {
            return "El carrito está vacío, no se puede procesar la orden.";
        }

        double total = carrito.calcularTotal();

        PagoStrategy estrategia;
        
        if (dto.getMetodoPago().equalsIgnoreCase("TARJETA")) {
            estrategia = new PagoTarjetaStrategy("Cliente", dto.getDatosPago());
        } else if (dto.getMetodoPago().equalsIgnoreCase("TRANSFERENCIA")) {
            estrategia = new PagoTransferenciaStrategy(dto.getDatosPago());
        } else {
            return "Método de pago no soportado.";
        }

        estrategia.procesarPago(total);

        carrito.getItems().clear();
        carritoRepo.save(carrito);

        return "Orden realizada con éxito. Total pagado: Q" + total;
    }
}
