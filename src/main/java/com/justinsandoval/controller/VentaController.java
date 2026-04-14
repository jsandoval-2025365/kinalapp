package com.justinsandoval.controller;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    private final IVentaService ventaService;

    public VentaController(IVentaService ventaService){
        this.ventaService = ventaService;
    }
    
        //Responde peticiones GET
    @GetMapping
    //ResponseEntity nos permite controlar el codigo HTTP y el cuerpo
    public ResponseEntity<List<Venta>> listar(){
        List<Venta> ventas = ventaService.listarTodos();
        //Delegamos al servicio
        return ResponseEntity.ok(ventas);
        // 200 OK con la lista de ventas
    }

    //{venta} es una variable de ruta(valor a buscar)
    @GetMapping("/{co_venta}")
    public ResponseEntity<Venta> buscarPorId(@PathVariable Long co_venta){
        //@PathVariable Toma el valor de la URL y lo asigna al dpi
        return ventaService.buscarPorDPI(co_venta)
                //Si Optional tiene valor, devuelve 200 Ok con el cliente
                .map(ResponseEntity::ok)
                //Si Optional esta vacío, devuelve 404 NOT FOUND
                .orElse(ResponseEntity.notFound().build());
    }

    //POST crear un nuevo cliente
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Venta venta){
        //@ResquestBody: Toma el JSON del cuerpo y lo convierte a un objeto de tipo Cliente
        //<?> significa "tipo generico" puede ser una Venta o un String
        try {
            Venta nuevaVenta = ventaService.guardar(venta);
            //Intentamos guardar el cliente pero puede lanzar una excepcion
            //de IllegalArgumentException
            return new ResponseEntity<>(nuevaVenta , HttpStatus.CREATED);
            //201 CREATED(mucho mas especifico que el 200 para la creacion de una venta)

        }catch(IllegalArgumentException e){
            //Si hay error de validacion
            //400 BAD REQUEST con el mensaje de error
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        // -> 

    }


    //DELETE para eliminar un cliente
    @DeleteMapping("/{co_venta}")
    public ResponseEntity<Void> eliminar(@PathVariable Long co_venta){
        //ResponseEntity<Void>: No devuelve cuerpo en la respuesta

        try{
            if(!ventaService.existePorDPI(co_venta)){
                return ResponseEntity.notFound().build();
                //404 Si no existe
            }
            ventaService.eliminar(co_venta);
            return ResponseEntity.noContent().build();
            //204 NO CONTENT(se ejecutó correctamente y no devuelve cuerpo)

        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
            //404 NOT FOUND
        }

    }

    //PUT Actualizar un cliente a traves del dpi
    @PutMapping("/{co_venta}")
    public ResponseEntity<?> actualizar(@PathVariable Long co_venta , @RequestBody Venta venta) {

        try {
            if(!ventaService.existePorDPI(co_venta)){
                //Verificar si existe antes de poder actualizar
                return ResponseEntity.notFound().build();
                //404 NOT FOUND
            }
            Venta ventaActualizada = ventaService.actualizar(co_venta , venta);
            return ResponseEntity.ok(ventaActualizada);
            //200 OK con la venta ya actualizado


        } catch (IllegalArgumentException e) {
            //Error cuando los datos sean incorrectos
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch(RuntimeException e){
            //Posiblemente cualquier otro error como: venta no encontrada, etc.
            //404 NOT FOUND
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/{dpi_cliente}")
    public ResponseEntity<List<Venta>> listarPorCliente(@PathVariable String dpi_cliente){
        List<Venta> ventas = ventaService.listarPorClienteDpi(dpi_cliente);
        if(ventas.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ventas);
    }
    

}
