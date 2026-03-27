package com.justinsandoval.controller;

import com.justinsandoval.entity.Usuario;
import com.justinsandoval.service.IUsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    private final IUsuarioService usuarioService;

    public UsuarioController(IUsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos(){

        List<Usuario> usuarios = usuarioService.listarTodos();
        
        return ResponseEntity.ok(usuarios);

    }

 
    @GetMapping("/{co_usuario}")
    public ResponseEntity<Usuario> buscarPorCodigo(@PathVariable int co_usuario){
    
        return usuarioService.buscarPorCodigo(co_usuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build()); 
    }


    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Usuario usuario){
        //@ResquestBody: Toma el JSON del cuerpo y lo convierte a un objeto de tipo Cliente
        //<?> significa "tipo generico" puede ser un Cliente o un String
        try {
            Usuario nuevoUsuario = usuarioService.guardar(usuario);
            //Intentamos guardar el cliente pero puede lanzar una excepcion
            //de IllegalArgumentException
            return new ResponseEntity<>(nuevoUsuario , HttpStatus.CREATED);
            //201 CREATED(mucho mas especifico que el 200 para la creacion de un cliente)

        }catch(IllegalArgumentException e){
            //Si hay error de validacion
            //400 BAD REQUEST con el mensaje de error
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }   



    @PutMapping("/{co_usuario}")
    public ResponseEntity<?> actualizar(@PathVariable String dpi , @RequestBody Usuario usuario) {

        try {
            if(!usuarioService.existePorCodigo(co_usuario)){
                //Verificar si existe antes de poder actualizar
                return ResponseEntity.notFound().build();
                //404 NOT FOUND
            }
            Usuario usuarioActualizado = usuarioService.actualizar(co_usuario , usuario);
            return ResponseEntity.ok(usuarioActualizado);
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



    @DeleteMapping("/{co_usuario}")
     public ResponseEntity<Void> eliminar(@PathVariable int co_usuario){
        //ResponseEntity<Void>: No devuelve cuerpo en la respuesta

        try{
            if(!usuarioService.existsPorCodigo(co_usuario)){
                return ResponseEntity.notFound().build();
                //404 Si no existe
            }
            usuarioService.eliminar(co_usuario);
            return ResponseEntity.noContent().build();
            //204 NO CONTENT(se ejecutó correctamente y no devuelve cuerpo)

        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
            //404 NOT FOUND
        }

    }
}