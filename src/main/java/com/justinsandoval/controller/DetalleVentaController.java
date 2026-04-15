package com.justinsandoval.controller;


import com.justinsandoval.entity.Cliente;
import com.justinsandoval.entity.DetalleVenta;
import com.justinsandoval.service.DetalleVentaService;
import com.justinsandoval.service.IDetalleVenta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalleVenta")
public class DetalleVentaController {   
    
    //Inyectamos el SERVICIO y NO el REPOSITORIO
    //El controlador solo debe de tener conexion con el servicio

    private final IDetalleVenta detalleVentaService;
    //Como buena practica la Inyeccion de dependencias debe hacerse por el constructor

    public DetalleVentaController(IDetalleVenta detalleVentaService) {
        this.detalleVentaService = detalleVentaService;
    }


    //Responde peticiones GET
    @GetMapping
    //ResponseEntity nos permite controlar el codigo HTTP y el cuerpo
    public ResponseEntity<List<DetalleVenta>> listar(){
        List<DetalleVenta> detalleVentas = detalleVentaService.listarTodos();
        //Delegamos al servicio
        return ResponseEntity.ok(detalleVentas);
        // 200 OK con la lista de detalleVenta
    }

    //{dpi} es una variable de ruta(valor a buscar)
    @GetMapping("/{co_detalle_venta}")
    public ResponseEntity<DetalleVenta> buscarPorCodigo(@PathVariable Long co_detalle_venta){
        //@PathVariable Toma el valor de la URL y lo asigna al dpi
        return detalleVentaService.buscarPorCodigo(co_detalle_venta)
                //Si Optional tiene valor, devuelve 200 Ok con el cliente
                .map(ResponseEntity::ok)
                //Si Optional esta vacío, devuelve 404 NOT FOUND
                .orElse(ResponseEntity.notFound().build());
    }

    //POST crear un nuevo cliente
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody DetalleVenta detalleVenta){
        //@ResquestBody: Toma el JSON del cuerpo y lo convierte a un objeto de tipo Cliente
        //<?> significa "tipo generico" puede ser un Cliente o un String
        try {
            DetalleVenta nuevoDetalleVenta = detalleVentaService.guardar(detalleVenta);
            //Intentamos guardar el cliente pero puede lanzar una excepcion
            //de IllegalArgumentException
            return new ResponseEntity<>(nuevoDetalleVenta , HttpStatus.CREATED);
            //201 CREATED(mucho mas especifico que el 200 para la creacion de un cliente)

        }catch(IllegalArgumentException e){
            //Si hay error de validacion
            //400 BAD REQUEST con el mensaje de error
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    //DELETE para eliminar un cliente
    @DeleteMapping("/{co_detalle_venta}")
    public ResponseEntity<Void> eliminar(@PathVariable Long co_detalle_venta){
        //ResponseEntity<Void>: No devuelve cuerpo en la respuesta

        try{
            if(!detalleVentaService.existByCodigo(co_detalle_venta)){
                return ResponseEntity.notFound().build();
                //404 Si no existe
            }
            detalleVentaService.eliminar(co_detalle_venta);
            return ResponseEntity.noContent().build();
            //204 NO CONTENT(se ejecutó correctamente y no devuelve cuerpo)

        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
            //404 NOT FOUND
        }

    }

    //PUT Actualizar un cliente a traves del dpi
    @PutMapping("/{co_detalle_venta}")
    public ResponseEntity<?> actualizar(@PathVariable Long co_detalle_venta , @RequestBody DetalleVenta detalleVenta) {

        try {
            if(!detalleVentaService.existByCodigo(co_detalle_venta)){
                //Verificar si existe antes de poder actualizar
                return ResponseEntity.notFound().build();
                //404 NOT FOUND
            }
            DetalleVenta detalleVentaActualizado = detalleVentaService.actualizar(co_detalle_venta , detalleVenta);
            return ResponseEntity.ok(detalleVentaActualizado);
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
