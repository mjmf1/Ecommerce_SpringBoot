package com.proyecto.ecommerce.springecommerce.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ordenes")
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    private String numero;
    private Date fechaCreacion;
    private Date fechaRecbida;
    private double total;

    @ManyToOne
    private  usuario usuario;

    @OneToMany(mappedBy = "orden")
    private List<DetalleOrden> detalle;

    public Orden(){

    }

    public Orden(Integer id, String numero, Date fechaCreacion, Date fechaRecbida, double total) {
        this.id = id;
        this.numero = numero;
        this.fechaCreacion = fechaCreacion;
        this.fechaRecbida = fechaRecbida;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaRecbida() {
        return fechaRecbida;
    }

    public void setFechaRecbida(Date fechaRecbida) {
        this.fechaRecbida = fechaRecbida;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }


    public com.proyecto.ecommerce.springecommerce.model.usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(com.proyecto.ecommerce.springecommerce.model.usuario usuario) {
        this.usuario = usuario;
    }


    public List<DetalleOrden> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<DetalleOrden> detalle) {
        this.detalle = detalle;
    }

    @Override
    public String toString() {
        return "Orden{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", fechaRecbida=" + fechaRecbida +
                ", total=" + total +
                '}';
    }
}
