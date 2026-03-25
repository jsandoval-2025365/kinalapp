package com.justinsandoval.entity;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @Column(name = "codigo_producto")
    private long co_producto;
    @Column(nullabled = true)
    private String nombre_producto;
    @Column
    private BigDecimal precio;
    @Column
    private int stock;
    @Column
    private int estado;

    public Producto(){


    }

    public Producto(long co_producto, String nombre_producto, BigDecimal precio, int stock, int estado) {
        this.co_producto = co_producto;
        this.nombre_producto = nombre_producto;
        this.precio = precio;
        this.stock = stock;
        this.estado = estado;
    }

    public long getCo_producto() {
        return co_producto;
    }

    public void setCo_producto(long co_producto) {
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
