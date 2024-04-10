package com.proyecto.ecommerce.springecommerce.service;

import com.proyecto.ecommerce.springecommerce.model.usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    List<usuario> findAll();
    Optional<usuario> findById(Integer id);
    usuario save(usuario usuario);
    Optional<usuario> findByEmail (String email);
}
