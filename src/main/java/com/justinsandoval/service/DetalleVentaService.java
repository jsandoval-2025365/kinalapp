package com.justinsandoval.service;

import com.justinsandoval.entity.DetalleVenta;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleVentaService implements IDetalleVenta{

    private final IDetalleVenta detalleVentaRepository;

    public DetalleVentaService(IDetalleVenta detalleVentaRepository){
        this.detalleVentaRepository = detalleVentaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetalleVenta> listarTodos() {
        return detalleVentaRepository.listarTodos();
    }


    @Override
    public DetalleVenta guardar(DetalleVenta detalleVenta) {

        return detalleVentaRepository.guardar(detalleVenta);
    }

    @Override
    public Optional<DetalleVenta> buscarPorCodigo(Long co_detalle_venta) {
        return detalleVentaRepository.buscarPorCodigo(co_detalle_venta);
    }

    @Override
    public DetalleVenta actualizar(Long co_detalle_venta, DetalleVenta detalleVenta) {
        if(!detalleVentaRepository.existByCodigo(co_detalle_venta)){
            throw new RuntimeException("El detalle venta no con el "+co_detalle_venta+" no existe");
        }
        detalleVenta.setCo_detalle_venta(co_detalle_venta);
        validarDetalleVenta(detalleVenta);
        return detalleVentaRepository.guardar(detalleVenta);
    }

    @Override
    public void eliminar(Long co_detalle_venta) {
        if(!detalleVentaRepository.existByCodigo(co_detalle_venta)){
            throw new RuntimeException("El detalleVenta con el codigo "+co_detalle_venta+" no se encotro");
        }
        detalleVentaRepository.eliminar(co_detalle_venta);

    }

    @Override
    @Transactional(readOnly = true)
    public boolean existByCodigo(Long co_detalle_venta) {
        return detalleVentaRepository.existByCodigo(co_detalle_venta);
    }

    private void validarDetalleVenta(DetalleVenta detalleVenta){
        if(detalleVenta.getCantidad() <= 0){
            throw new IllegalArgumentException("La cantidad de la venta no puede ser menor a cero");
        }
        if(detalleVenta.getPrecio_unitario() == null){
            throw new IllegalArgumentException("El precio unitario no puede estar vacío");
        }
    }

    
}
