package com.proyecto.ecommerce.springecommerce.repository;

import com.proyecto.ecommerce.springecommerce.model.producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface productoRepository extends JpaRepository<producto, Integer> {
}

