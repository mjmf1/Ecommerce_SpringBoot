package com.proyecto.ecommerce.springecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.ecommerce.springecommerce.model.usuario;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IusuarioRepository extends JpaRepository <usuario, Integer>  {
    Optional<usuario> findByEmail (String email);

}

