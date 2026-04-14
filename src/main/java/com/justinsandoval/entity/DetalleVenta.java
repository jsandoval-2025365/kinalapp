package com.justinsandoval.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "detalle_ventas")
public class DetalleVenta {

    @Id
    @Column(name = "codigo_detalle_venta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long co_detalle_venta;

    @Column(nullable = false)
    private Long cantidad;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal precio_unitario;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal subtotal;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "Productos_codigo_producto", nullable = false)
    private Producto producto;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "Ventas_codigo_venta", nullable = false) 
    private Venta venta;

    //Constructor VACIO
    public DetalleVenta() {

    }

    //Constructor LLENO


    public DetalleVenta(Long co_detalle_venta, Long cantidad,
                        BigDecimal precio_unitario, BigDecimal subtotal,
                        Producto producto, Venta venta) {
        this.co_detalle_venta = co_detalle_venta;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
        this.subtotal = subtotal;
        this.producto = producto;
        this.venta = venta;
    }


    //Métodos de acceso GETTERS AND SETTERS
    public Long getCo_detalle_venta() {
        return co_detalle_venta;
    }

    public void setCo_detalle_venta(Long co_detalle_venta) {
        this.co_detalle_venta = co_detalle_venta;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(BigDecimal precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
}
