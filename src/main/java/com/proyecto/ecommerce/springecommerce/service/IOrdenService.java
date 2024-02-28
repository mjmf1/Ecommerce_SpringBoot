package com.proyecto.ecommerce.springecommerce.service;

import com.proyecto.ecommerce.springecommerce.model.Orden;

import java.util.List;

public interface IOrdenService {
    List<Orden> findAll();
Orden save(Orden orden);
}
