package com.proyecto.ecommerce.springecommerce.service;

import com.proyecto.ecommerce.springecommerce.model.Orden;
import com.proyecto.ecommerce.springecommerce.model.usuario;

import java.util.List;

public interface IOrdenService {
    List<Orden> findAll();
Orden save(Orden orden);
    String generarNumeroOrden();
    List<Orden> findByUsuario (usuario usuario);
}
