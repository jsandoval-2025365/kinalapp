package com.justinsandoval.repository;

import com.justinsandoval.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario , int> {
}

