package com.proyecto.ecommerce.springecommerce.service;

import com.proyecto.ecommerce.springecommerce.model.Orden;
import com.proyecto.ecommerce.springecommerce.model.usuario;

import java.util.List;
import java.util.Optional;

public interface IOrdenService {
    List<Orden> findAll();
    Optional<Orden> findById(Integer id);
    Orden save(Orden orden);
    String generarNumeroOrden();
    List<Orden> findByUsuario (usuario usuario);
}
