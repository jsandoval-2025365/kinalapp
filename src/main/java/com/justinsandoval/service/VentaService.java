package com.justinsandoval.service;


import com.justinsandoval.entity.Venta;
import com.justinsandoval.repository.IVentaRepository;

import java.util.List;
import java.util.Optional;

@service
@Transactional
public class VentaService implements IVentaService {

    private final IVentaRepository ventaRepository;

    public VentaService(IVentaRepository ventaRepository){
        this.ventaRepository = ventaRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Venta> listarTodos() {
        return ventaRepository.findAll();
    }


    @Override
    public Venta guardar(Venta venta) {
        
        validarVenta(venta);
       
        if(venta.getEstado() == 0){
            venta.setEstado((Long)1); // 1 = Activo
        }
        
        return ventaRepository.save(venta);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Venta> buscarPorCodigo(Long co_venta) {
        
        return ventaRepository.findByCo_venta(co_venta);
    }


    @Override
    public Venta actualizar(Long co_venta, Venta venta) {
        
        if(!ventaRepository.existsById(co_venta)){
            throw new IllegalArgumentException("No existe una venta con el codigo: " + co_venta);
        }

        venta.setCo_venta(co_venta);
        
        validarVenta(venta);
        
        return ventaRepository.save(venta);
    }


    @Override
    public void eliminar(Long co_venta) {
        if(!ventaRepository.existsById(co_venta)){
            throw new IllegalArgumentException("No existe una venta con el codigo: " + co_venta);
        }

        ventaRepository.deleteById(co_venta);   
    }


    @Override
    @Transactional(readOnly = true)
    public boolean existePorCodigo(Long co_venta) {
        return ventaRepository.existsById(co_venta);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorClienteDpi(String dpi) {
        return ventaRepository.existsByClienteDpi(dpi);
    }   
    


    private void validarVenta(Venta venta){

        if (venta.getCliente() == null || venta.getCliente().getDpi() == null || venta.getCliente().getDpi().isEmpty()){
            throw new IllegalArgumentException("El cliente es obligatorio");
        }   

        if (venta.getDetalleVentas() == null || venta.getDetalleVentas().isEmpty()){
            throw new IllegalArgumentException("La venta debe tener al menos un detalle");
        }   

        if (venta.getFecha() == null){
            throw new IllegalArgumentException("La fecha de la venta es obligatoria");
        }   
        
        if (venta.getTotal() == null || venta.getTotal().compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("El total de la venta debe ser mayor a cero");
        }
        
         if (venta.getCo_venta() != null && ventaRepository.existsById(venta.getCo_venta())){
            throw new IllegalArgumentException("Ya existe una venta con el codigo: " + venta.getCo_venta());
        }

        if (!ventaRepository.existsByClienteDpi(venta.getCliente().getDpi())){
            throw new IllegalArgumentException("No existe un cliente con el DPI: " + venta.getCliente().getDpi());
        }   

    }

}
