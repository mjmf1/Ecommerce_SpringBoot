package com.proyecto.ecommerce.springecommerce.controller;

import com.proyecto.ecommerce.springecommerce.model.usuario;
import com.proyecto.ecommerce.springecommerce.service.IUsuarioService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;

import java.util.Optional;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
    @Autowired
    private IUsuarioService usuarioService;

    //usuario/registro
    @GetMapping("/registro")
    public String create() {
        return "usuario/registro";
    }

    @PostMapping("/save")
    public String save(usuario usuario) {
        logger.info("Usuario registro: {}", usuario);
        usuario.setTipo("USER");
        usuarioService.save(usuario);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(){
        return "usuario/login";
    }
    @PostMapping("/acceder")
    public String acceder(usuario usuario , HttpSession session){
        logger.info("Accesos : {}", usuario);

        Optional<usuario> user =usuarioService.findByEmail(usuario.getEmail());
        //logger.info("usuario de db: {}", user.get());

        if (user.isPresent()){
            session.setAttribute("idusuario", user.get().getId());
            if (user.get().getTipo().equals("ADMIN")){
                return "redirect:/admin";
            }else{
                return "redirect:/";
            }
        }else {
            logger.info("usuario de no existe");
        }

        return "redirect:/";
    }
}
