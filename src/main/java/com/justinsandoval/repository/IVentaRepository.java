package com.justinsandoval.repository;


import com.justinsandoval.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVentaRepository extends JpaRepository<Venta, Long> {
    
}
