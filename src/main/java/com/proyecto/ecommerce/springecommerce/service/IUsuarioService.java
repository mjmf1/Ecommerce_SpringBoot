package com.proyecto.ecommerce.springecommerce.service;

import com.proyecto.ecommerce.springecommerce.model.usuario;

import java.util.Optional;

public interface IUsuarioService {
    Optional<usuario> findById(Integer id);
    usuario save(usuario usuario);
    Optional<usuario> findByEmail (String email);
}
