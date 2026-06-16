package com.justinsandoval.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.justinsandoval.entity.Cliente;
import com.justinsandoval.entity.Producto;
import com.justinsandoval.repository.IProductoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductoService implements IProductoServices {
    
    private final IProductoRepository productoRepository;

    public IProductoRepository(IProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }


    @Override
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    

    @Override
    public Producto guardar(Producto producto) {
        /*
        * Metodo de guardar, crea un Cliente
        * Acá es donde colocamos la lógica del negocio antes de guard   ar
        * Pero primero validamos el dato
        **/
        validarProducto(producto);
        if(producto.getEstado() == 0)
            producto.setEstado((long)1);
        return productoRepository.save(producto);


    }

    @Override
    public Optional<Producto> buscarPorProducto(Long co_producto) {
        //Buscar un cliente por ID
        return productoRepository.findById(co_producto

        );


    }




    @Override
    public Producto actualizar(Long co_producto, Producto producto) {
        //Metodo para actualizar un cliente existente
        if(!productoRepository.existsById(co_producto)){
            throw new RuntimeException("El cliente no se encontro con el Dpi: "+co_producto);
            //Si no existe se lanza una excepcion(error controlado)
        }
        producto.setCo_producto(co_producto);
        //Aseguramos que el DPI del objeto conincida con el de la URL
        //Por seguridad usamos el DPI de la URL y no el que viene en el JSON
        validarProducto(producto);

        return productoRepository.save(producto);
        /*
         * save() este no solo sirve para guardar sino tambien para actualizar Si el dato
         * Existe (dpi) entonces hace UPDATE pero si no existe hace un INSERT pero
         * antes verificamos si existe o no el cliente
         **/

    }

    @Override
    public void eliminar(Long co_producto) {
        //Elimina por Dpi
        if(!productoRepository.existsById(co_producto)){
            throw new RuntimeException("El cliente no se encontro con el Dpi: "+co_producto);
        }
        productoRepository.deleteById(co_producto);
    }

    @Override
    public boolean existsByProducto(Long co_producto) {
        //Verificar si existe un cliente
        return productoRepository.existsById(co_producto);
    }

    //Metodo privado(solo puede utilzarse dentro de la clase)
    private void validarProducto(Producto producto){
        /*
        * Validaciones del negocio: Este metodo se hará privado porque
        * es algo interno del servicio
        **/
        if(producto.getCo_producto() == null || (producto.getCo_producto() <= 0)){
            //Si el dpi es null o esta vacio despues de quitar espacios
            //Lanza una excepcion con un mensaje
            throw new IllegalArgumentException("El código del producto es obligatorio");

        }
        if(producto.getNombre_producto() == null || producto.getNombre_producto().trim().isEmpty() ){
            throw new IllegalArgumentException("El nombre del producto es obligatorio");

        }
        if(producto.getPrecio() == null || producto.getPrecio().compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("El precio no puede ser vacio o menor a cero");

        }
        if(producto.getStock() < 0)
            throw new IllegalArgumentException("El Stock no puede ser negativo");


    }




}
