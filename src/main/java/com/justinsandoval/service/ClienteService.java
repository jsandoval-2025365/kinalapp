package com.justinsandoval.service;

import com.justinsandoval.entity.Cliente;
import com.justinsandoval.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteService implements IClienteService{

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    // Modificando
    @Override
    public List<Cliente> listarEstado() {
        return clienteRepository.findByEstado((long) 1);
    }




    @Override
    public Cliente guardar(Cliente cliente) {
        /*
        * Metodo de guardar, crea un Cliente
        * Acá es donde colocamos la lógica del negocio antes de guard   ar
        * Pero primero validamos el dato
        **/
        validarCliente(cliente);
        if(cliente.getEstado() == 0)
            cliente.setEstado((long)1);
        return clienteRepository.save(cliente);


    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> buscarPorDPI(String dpi) {
        //Buscar un cliente por ID
        return clienteRepository.findById(dpi);


    }




    @Override
    public Cliente actualizar(String dpi, Cliente cliente) {
        //Metodo para actualizar un cliente existente
        if(!clienteRepository.existsById(dpi)){
            throw new RuntimeException("El cliente no se encontro con el Dpi: "+dpi);
            //Si no existe se lanza una excepcion(error controlado)
        }
        cliente.setDPICliente(dpi);
        //Aseguramos que el DPI del objeto conincida con el de la URL
        //Por seguridad usamos el DPI de la URL y no el que viene en el JSON
        validarCliente(cliente);

        return clienteRepository.save(cliente);
        /*
         * save() este no solo sirve para guardar sino tambien para actualizar Si el dato
         * Existe (dpi) entonces hace UPDATE pero si no existe hace un INSERT pero
         * antes verificamos si existe o no el cliente
         **/

    }

    @Override
    public void eliminar(String dpi) {
        //Elimina por Dpi
        if(!clienteRepository.existsById(dpi)){
            throw new RuntimeException("El cliente no se encontro con el Dpi: "+dpi);
        }
        clienteRepository.deleteById(dpi);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorDPI(String dpi) {
        //Verificar si existe un cliente
        return clienteRepository.existsById(dpi);
    }

    //Metodo privado(solo puede utilzarse dentro de la clase)
    private void validarCliente(Cliente cliente){
        /*
        * Validaciones del negocio: Este metodo se hará privado porque
        * es algo interno del servicio
        **/
        if(cliente.getDPICliente() == null || (cliente.getDPICliente().trim().isEmpty())){
            //Si el dpi es null o esta vacio despues de quitar espacios
            //Lanza una excepcion con un mensaje
            throw new IllegalArgumentException("El DPI es un dato obligatorio");

        }
        if(cliente.getNombreCliente() == null || cliente.getNombreCliente().trim().isEmpty() ){
            throw new IllegalArgumentException("El nombre es un dato obligatorio");

        }
        if(cliente.getApellidoCliente() == null || cliente.getApellidoCliente().trim().isEmpty()){
            throw new IllegalArgumentException("El apellido es un dato obligatorio");

        }

    }


}
