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
    private long co_usuario;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String rol;
    @Column
    private int estado;

    //Constructor vacio
    public Usuario(){

    }

    //Constructor lleno
    public Usuario(int co_usuario, String username, String password, String email, String rol, int estado) {
        this.co_usuario = co_usuario;
        this.username = username;
        this.password = password;
        this.email = email;
        this.rol = rol;
        this.estado = estado;
    }


    //Metodos de Acceso Getters and Setters
    public int getCo_usuario() {
        return co_usuario;
    }

    public void setCo_usuario(int co_usuario) {
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
