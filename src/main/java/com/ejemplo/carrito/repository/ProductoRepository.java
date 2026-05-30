package com.ejemplo.carrito.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ejemplo.carrito.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> { }