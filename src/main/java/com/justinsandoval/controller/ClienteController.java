package com.justinsandoval.controller;

import com.justinsandoval.entity.Cliente;
import com.justinsandoval.service.IClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RestController = @Controller + @RequestBody
@RequestMapping("/clientes")
//Todas las rutas en este controlador deben empezar por /clientes
public class ClienteController {

    //Inyectamos el SERVICIO y NO el REPOSITORIO
    //El controlador solo debe de tener conexion con el servicio

    private final IClienteService clienteService;
    //Como buena practica la Inyeccion de dependencias debe hacerse por el constructor

    public ClienteController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }


    //Responde peticiones GET
    @GetMapping
    //ResponseEntity nos permite controlar el codigo HTTP y el cuerpo
    public ResponseEntity<List<Cliente>> listar(){
        List<Cliente> clientes = clienteService.listarTodos();
        //Delegamos al servicio
        return ResponseEntity.ok(clientes);
        // 200 OK con la lista de clientes
    }

    //{dpi} es una variable de ruta(valor a buscar)
    @GetMapping("/{dpi}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable String dpi){
        //@PathVariable Toma el valor de la URL y lo asigna al dpi
        return clienteService.buscarPorDPI(dpi)
                //Si Optional tiene valor, devuelve 200 Ok con el cliente
                .map(ResponseEntity::ok)
                //Si Optional esta vacío, devuelve 404 NOT FOUND
                .orElse(ResponseEntity.notFound().build());
    }

    //POST crear un nuevo cliente
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Cliente cliente){
        //@ResquestBody: Toma el JSON del cuerpo y lo convierte a un objeto de tipo Cliente
        //<?> significa "tipo generico" puede ser un Cliente o un String
        try {
            Cliente nuevoCliente = clienteService.guardar(cliente);
            //Intentamos guardar el cliente pero puede lanzar una excepcion
            //de IllegalArgumentException
            return new ResponseEntity<>(nuevoCliente , HttpStatus.CREATED);
            //201 CREATED(mucho mas especifico que el 200 para la creacion de un cliente)

        }catch(IllegalArgumentException e){
            //Si hay error de validacion
            //400 BAD REQUEST con el mensaje de error
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    //DELETE para eliminar un cliente
    @DeleteMapping("/{dpi}")
    public ResponseEntity<Void> eliminar(@PathVariable String dpi){
        //ResponseEntity<Void>: No devuelve cuerpo en la respuesta

        try{
            if(!clienteService.existePorDPI(dpi)){
                return ResponseEntity.notFound().build();
                //404 Si no existe
            }
            clienteService.eliminar(dpi);
            return ResponseEntity.noContent().build();
            //204 NO CONTENT(se ejecutó correctamente y no devuelve cuerpo)

        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
            //404 NOT FOUND
        }

    }

    //PUT Actualizar un cliente a traves del dpi
    @PutMapping("/{dpi}")
    public ResponseEntity<?> actualizar(@PathVariable String dpi , @RequestBody Cliente cliente) {

        try {
            if(!clienteService.existePorDPI(dpi)){
                //Verificar si existe antes de poder actualizar
                return ResponseEntity.notFound().build();
                //404 NOT FOUND
            }
            Cliente clienteActualizado = clienteService.actualizar(dpi , cliente);
            return ResponseEntity.ok(clienteActualizado);
            //200 OK con el cliente ya actualizado


        } catch (IllegalArgumentException e) {  
            //Error cuando los datos sean incorrectos
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch(RuntimeException e){
            //Posiblemente cualquier otro error como: Cliente no encontrado, etc.
            //404 NOT FOUND
            return ResponseEntity.notFound().build();
        }
    }

    //Mostrar Solo Los Cliente Activos
    @GetMapping("/estado")
    public ResponseEntity<List<Cliente>> activos(){
        //@PathVariable Toma el valor de la URL y lo asigna al dpi
        List<Cliente> activos = clienteService.listarEstado();
        return ResponseEntity.ok(activos);
    }



}
