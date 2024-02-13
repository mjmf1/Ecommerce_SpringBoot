package com.proyecto.ecommerce.springecommerce.service;

import com.proyecto.ecommerce.springecommerce.model.producto;
import com.proyecto.ecommerce.springecommerce.repository.productoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductoServiceImpl  implements ProductoService{

    @Autowired
    private productoRepository productoRepository;
    @Override
    public producto save(producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Optional<producto> get(Integer id) {
        return productoRepository.findById(id);

    }

    @Override
    public void update(producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public void delete(Integer id) {
        productoRepository.deleteById(id);
    }
}
