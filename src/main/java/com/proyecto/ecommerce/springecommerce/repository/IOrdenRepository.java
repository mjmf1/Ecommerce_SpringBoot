package com.proyecto.ecommerce.springecommerce.repository;

import com.proyecto.ecommerce.springecommerce.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrdenRepository extends JpaRepository <Orden, Integer> {
}
