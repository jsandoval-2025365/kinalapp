package com.justinsandoval.service;

import com.justinsandoval.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    List<Usuario> listarTodos();

    Usuario guardar (Usuario usuario);

    Optional<Usuario> buscarPorCodigo(int co_usuario);

    Usuario actualizar(int co_usuario, Usuario usuario);

    void eliminar(int co_usuario);

    boolean existePorCodigo(int co_usuario);


    

}
