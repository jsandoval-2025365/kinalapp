package com.justinsandoval.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.justinsandoval.entity.Venta;

public interface IDetalleVenta extends JpaRepository<Venta, Long> {
    
}
