package com.justinsandoval.service;

import com.justinsandoval.entity.Venta;
import java.util.List;
import java.util.Optional;

public interface IVentaService {

    List<Venta> listarTodos();

    Venta guardar (Venta venta);

    Optional<Venta> buscarPorCodigo(Long co_venta);

    Venta actualizar(Long co_venta, Venta venta);

    void eliminar(Long co_venta);

    boolean existePorCodigo(Long co_venta);
    
    
}
