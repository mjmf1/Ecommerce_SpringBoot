package com.proyecto.ecommerce.springecommerce.service;

import com.proyecto.ecommerce.springecommerce.model.producto;
import com.proyecto.ecommerce.springecommerce.repository.IproductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl  implements ProductoService{

    @Autowired
    private IproductoRepository productoRepository;
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

    @Override
    public List<producto> findAll() {
        return productoRepository.findAll();
    }
}
