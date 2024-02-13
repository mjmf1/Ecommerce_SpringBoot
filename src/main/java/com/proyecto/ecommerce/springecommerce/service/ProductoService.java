package com.proyecto.ecommerce.springecommerce.service;

import com.proyecto.ecommerce.springecommerce.model.producto;

import java.util.Optional;

public interface ProductoService {
    public producto save(producto producto);
    public Optional<producto> get(Integer id);
    public void update(producto producto);
    public void delete(Integer id);

}