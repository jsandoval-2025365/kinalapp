package com.justinsandoval.service;

import java.util.List;
import java.util.Optional;

import com.justinsandoval.entity.Cliente;
import com.justinsandoval.entity.Producto;

public interface IProductoServices {

    /*
     * Interfaz: Es un contrato que dice QUÉ métodos debe tenes
     *  cualquier servicio de Clientes, No tiene
     * Implementacion, solo la definicion de los métodos
     */

    //Metodo que devuelve una lista de todos los Clientes
    List<Producto> listarTodos();
    /*
     *List<Cliente> lo que hace es devolver una lista
     *  de objetos de la entidad Clientes
     */

    //Metodo que guarda un Cliente en la BD
    Producto guardar (Producto producto);
    //Parametros: Recibe un objeto Cliente con los datos a
    //guardar

    //Optional - Contenedor que puede o no tener valor
    //evita el error de NullPointerException
    Optional<Producto> buscarPorProducto(Long co_producto);


    //Metodo que actualiza un Cliente
    Producto actualizar(Long co_producto, Producto producto);
    /*
     * Parametros - dpi: DPI del cliente a actualizar
     * Cliente cliente: Objeto con los datos nuevos
     *  Retorna un objeto de tipo Cliente ya aztualizado
     */

    /*
     * Metodo de tipo void para eliminar a un CLiente
     * void: no retorna ningun valor o dato
     * Eliminar un Cliente por su DPI
     */
    void eliminar(Long co_producto);

    //boolean - Retorna true si existe y false si no existe
    boolean existsByProducto (Long co_producto);



    

    
}
