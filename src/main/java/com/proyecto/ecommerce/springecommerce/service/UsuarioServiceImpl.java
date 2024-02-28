package com.proyecto.ecommerce.springecommerce.service;

import com.proyecto.ecommerce.springecommerce.model.usuario;
import com.proyecto.ecommerce.springecommerce.repository.IusuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
    @Autowired
    private IusuarioRepository usuarioRepository;

    @Override
    public Optional<usuario> findById(Integer id) {
        return usuarioRepository.findById(id);
    }
}

