package com.justinsandoval.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @Column(name = "codigo_producto")
    private Long co_producto;
    @Column
    private String nombre_producto;
    @Column(precision = 10 , scale = 2)
    private BigDecimal precio;
    @Column
    private int stock;
    @Column
    private int estado;

    public Producto(){


    }

    public Producto(Long co_producto, String nombre_producto, BigDecimal precio, int stock, int estado) {
        this.co_producto = co_producto;
        this.nombre_producto = nombre_producto;
        this.precio = precio;
        this.stock = stock;
        this.estado = estado;
    }

    public Long getCo_producto() {
        return co_producto;
    }

    public void setCo_producto(Long co_producto) {
        this.co_producto = co_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
