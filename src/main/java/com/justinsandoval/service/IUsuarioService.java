package com.justinsandoval.service;

import com.justinsandoval.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    List<Usuario> listarTodos();

    Usuario guardar (Usuario usuario);

    Optional<Usuario> buscarPorCodigo(Long co_usuario);

    Usuario actualizar(Long co_usuario, Usuario usuario);

    void eliminar(Long co_usuario);

    boolean existePorCodigo(Long co_usuario);


    

}
