package com.justinsandoval.controller;


import com.justinsandoval.entity.Producto;
import com.justinsandoval.service.IProductoServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RestController = @Controller + @RequestBody
@RequestMapping("/productos")
//Todas las rutas en este controlador deben empezar por /clientes
public class ProductoController {

    //Inyectamos el SERVICIO y NO el REPOSITORIO
    //El controlador solo debe de tener conexion con el servicio

    private final IProductoServices productoServices;
    //Como buena practica la Inyeccion de dependencias debe hacerse por el constructor

    public ProductoController(IProductoServices productoServices) {
        this.productoServices = productoServices;
    }


    //Responde peticiones GET
    @GetMapping
    //ResponseEntity nos permite controlar el codigo HTTP y el cuerpo
    public ResponseEntity<List<Producto>> listar(){
        List<Producto> productos = productoServices.listarTodos();
        //Delegamos al servicio
        return ResponseEntity.ok(productos);
        // 200 OK con la lista de clientes
    }

    //{dpi} es una variable de ruta(valor a buscar)
    @GetMapping("/{co_producto}")
    public ResponseEntity<Producto> buscarPorId(@PathVariable Long co_producto){
        //@PathVariable Toma el valor de la URL y lo asigna al dpi
        return productoServices.buscarPorProducto(co_producto)
                //Si Optional tiene valor, devuelve 200 Ok con el cliente
                .map(ResponseEntity::ok)
                //Si Optional esta vacío, devuelve 404 NOT FOUND
                .orElse(ResponseEntity.notFound().build());
    }

    //POST crear un nuevo cliente
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Producto producto){
        //@ResquestBody: Toma el JSON del cuerpo y lo convierte a un objeto de tipo Cliente
        //<?> significa "tipo generico" puede ser un Cliente o un String
        try {
            Producto nueovProducto = productoServices.guardar(producto);
            //Intentamos guardar el cliente pero puede lanzar una excepcion
            //de IllegalArgumentException
            return new ResponseEntity<>(nueovProducto , HttpStatus.CREATED);
            //201 CREATED(mucho mas especifico que el 200 para la creacion de un cliente)

        }catch(IllegalArgumentException e){
            //Si hay error de validacion
            //400 BAD REQUEST con el mensaje de error
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    //DELETE para eliminar un cliente
    @DeleteMapping("/{co_producto}")
    public ResponseEntity<Void> eliminar(@PathVariable Long co_producto){
        //ResponseEntity<Void>: No devuelve cuerpo en la respuesta

        try{
            if(!productoServices.existsByProducto(co_producto)){
                return ResponseEntity.notFound().build();
                //404 Si no existe
            }
            productoServices.eliminar(co_producto);
            return ResponseEntity.noContent().build();
            //204 NO CONTENT(se ejecutó correctamente y no devuelve cuerpo)

        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
            //404 NOT FOUND
        }

    }

    //PUT Actualizar un cliente a traves del dpi
    @PutMapping("/{co_producto}")
    public ResponseEntity<?> actualizar(@PathVariable Long co_producto , @RequestBody Producto producto) {

        try {
            if(!productoServices.existsByProducto(co_producto)){
                //Verificar si existe antes de poder actualizar
                return ResponseEntity.notFound().build();
                //404 NOT FOUND
            }
            Producto productoActualizado = productoServices.actualizar(co_producto , producto);
            return ResponseEntity.ok(productoActualizado);
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

    



}
