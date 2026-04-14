package com.justinsandoval.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import jakarta.persistence.*;
import org.hibernate.annotations.AnyDiscriminatorImplicitValues.Strategy;

@Entity
@Table(name = "ventas")
public class Venta {

     @Id
     @Column(name = "codigo_venta")
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long co_venta;

     @Column(nullable = false)
     private Date fecha_venta;

     @Column(precision = 10, scale = 2, nullable = false)
     private BigDecimal total;

     @Column(nullable = false)
     private Long estado;


     /**
      * Relación ManyToOne con Cliente
      * Muchas ventas pueden pertenecer a un mismo cliente
      */
     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "Clientes_dpi_cliente", nullable = false)
     private Cliente cliente;

     /**
      * Relación ManyToOne con Usuario
      * Muchas ventas pueden ser realizadas por un mismo usuario
      */
     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "Usuarios_codigo_usuario", nullable = false)
     private Usuario usuario;

     //Constructor VACIO
     public Venta(){

     }

     //Constructor LLENO
     public Venta(Long co_venta, Date fecha_venta, BigDecimal total, Long estado, Cliente cliente, Usuario usuario) {
          this.co_venta = co_venta;
          this.fecha_venta = fecha_venta;
          this.total = total;
          this.estado = estado;
          this.cliente = cliente;
          this.usuario = usuario;
     }

     //Métodos de acceso GETTERS AND SETTERS
     public Long getCo_venta() {
          return co_venta;
     }

     public void setCo_venta(Long co_venta) {
          this.co_venta = co_venta;
     }

     public Date getFecha_venta() {
          return fecha_venta;
     }

     public void setFecha_venta(Date fecha_venta) {
          this.fecha_venta = fecha_venta;
     }

     public BigDecimal getTotal() {
          return total;
     }

     public void setTotal(BigDecimal total) {
          this.total = total;
     }

     public Long getEstado() {
          return estado;
     }

     public void setEstado(Long estado) {
          this.estado = estado;
     }

     public Cliente getCliente() {
          return cliente;
     }

     public void setCliente(Cliente cliente) {
          this.cliente = cliente;
     }

     public Usuario getUsuario() {
          return usuario;
     }

     public void setUsuario(Usuario usuario) {
          this.usuario = usuario;
     }
}
