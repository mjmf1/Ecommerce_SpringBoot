package com.proyecto.ecommerce.springecommerce.repository;

import com.proyecto.ecommerce.springecommerce.model.Orden;
import com.proyecto.ecommerce.springecommerce.model.usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrdenRepository extends JpaRepository <Orden, Integer> {
    List<Orden> findByUsuario (usuario usuario);
}
