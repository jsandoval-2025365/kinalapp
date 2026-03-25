package com.justinsandoval.service;

import com.justinsandoval.entity.Usuario;
import com.justinsandoval.repository.IUsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService implements IUsuarioService {

    private final IUsuarioRepository usuarioRepository;

    public UsuarioService(IUsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }



    @Override
    public Usuario guardar(Usuario usuario) {
        
        validarUsuario(usuario);
        if(cliente.getEstado() == 0)
            cliente.setEstado(1);
        
        return clienteRepository.save(cliente);

    }


    @Transactional(readOnly = true)
    @Override
    public Optional<Usuario> buscarPorCodigo(int co_usuario) {
        return clienteRepository.findById(co_usuario);
    
    }



    @Override
    public Usuario actualizar(int co_usuario, Usuario usuario) {
        
        if(!usuarioRepository.existsById(co_usuario)){
            throw new RuntimeException("El usuario con el codigo " + co_usuario + "no existe");
        }

        cliente.setCo_usuario(co_usuario);
        validarUsuario(usuario);
        
        return clienteRepository.save(usuario);
    }



    @Override
    public void eliminar(int co_usuario) {
        if(!clienteRepository.existsById(co_usuario)){
            throw new RuntimeException("El usuario no se encontro con el codigo: "+co_usuario)
        }

        clienteRepository.deleteById(co_usuario);

    }


    
    @Override
    @Transactional(readOnly = true)
    public boolean existePorCodigo(int co_usuario) {
        return clienteRepository.existexistsById(co_usuario);
    }


    
    private void validarUsuario(Usuario usuario){
        
        if(usuario.getCo_usuario() == null || usuario.getCo_usuario().trim().isEmpty() ){
            throw new IllegalArgumentException("El codigo de identificación del usuario es un dato obligatorio");

        }

        if(usuario.getUsername() == null || usuario.getUsername().trim().isEmpty() ){
            throw new IllegalArgumentException("El nombre de usuario es un dato obligatorio");

        }
        
        if(usuario.getPassword() == null || usuario.getPassword().trim().isEmpty() ){
            throw new IllegalArgumentException("La contraseña es un dato obligatorio");

        }   

        if(usuario.getEmail() == null || usuario.getEmail().trim().isEmpty() ){
            throw new IllegalArgumentException("El correo electrónico es un dato obligatorio");

        }   

    
    }

}
