package com.justinsandoval.service;

import java.util.List;
import java.util.Optional;

import com.justinsandoval.entity.DetalleVenta;

public interface IDetalleVenta {

    List<DetalleVenta> listarTodos();

    DetalleVenta guardar(DetalleVenta detalleVenta);

    Optional<DetalleVenta> buscarPorCodigo(Long co_detalle_venta);
    
    DetalleVenta actualizar(Long co_detalle_venta, DetalleVenta detalleVenta);

    void eliminar(Long co_detalle_venta);

    boolean existByCodigo(Long co_detalle_venta);
    
}
