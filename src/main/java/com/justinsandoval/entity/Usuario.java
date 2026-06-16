package com.justinsandoval.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "usuarios")
public class Usuario {

    //Atributos
    @Id
    @Column(name = "codigo_usuario")
    private Long co_usuario;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String rol;
    @Column(nullable = false)
    private Long estado;

    //Constructor vacio
    public Usuario(){

    }

    //Constructor lleno
    public Usuario(Long co_usuario, String username, String password, String email, String rol, Long estado) {
        this.co_usuario = co_usuario;
        this.username = username;
        this.password = password;
        this.email = email;
        this.rol = rol;
        this.estado = estado;
    }


    //Metodos de Acceso Getters and Setters
    public Long getCo_usuario() {
        return co_usuario;
    }

    public void setCo_usuario(Long co_usuario) {
        this.co_usuario = co_usuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Long getEstado() {
        return estado;
    }

    public void setEstado(Long estado) {
        this.estado = estado;
    }
}
