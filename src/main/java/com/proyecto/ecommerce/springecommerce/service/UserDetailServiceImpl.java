package com.proyecto.ecommerce.springecommerce.service;

import com.proyecto.ecommerce.springecommerce.controller.AdminController;
import com.proyecto.ecommerce.springecommerce.model.usuario;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl  implements UserDetailService{
    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private BCryptPasswordEncoder bCrypt;
    @Autowired
    HttpSession session;

    private Logger logg = LoggerFactory.getLogger(UserDetailServiceImpl.class);
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        logg.info("Este es el Username");
        Optional<usuario> optionalUser =  usuarioService.findByEmail(username);
        if (optionalUser.isPresent()){
            logg.info("Esto es el id del usuario {}", optionalUser.get().getId());
            session.setAttribute("idusuario", optionalUser.get().getId());
            usuario usuario = optionalUser.get();
            return User.builder().username(usuario.getNombre()).password(bCrypt.encode(usuario.getPassword())).roles(usuario.getTipo()).build();
        }else{
            throw  new UsernameNotFoundException("Usuario no encontrado");
        }

    }
}
