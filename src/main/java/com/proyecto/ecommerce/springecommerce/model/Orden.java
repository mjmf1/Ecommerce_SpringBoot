package com.proyecto.ecommerce.springecommerce.model;

import java.util.Date;

public class Orden {
    private  Integer id;
    private String numero;
    private Date fechaCreacion;
    private Date fechaRecbida;
    private double total;

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
