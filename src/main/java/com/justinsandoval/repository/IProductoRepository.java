package com.justinsandoval.repository;

import com.justinsandoval.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoRepository extends JpaRepository<Producto,Long> {
    
}
