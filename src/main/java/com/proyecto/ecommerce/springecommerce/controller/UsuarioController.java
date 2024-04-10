package com.proyecto.ecommerce.springecommerce.controller;


import com.proyecto.ecommerce.springecommerce.model.Orden;
import com.proyecto.ecommerce.springecommerce.model.usuario;
import com.proyecto.ecommerce.springecommerce.service.IOrdenService;
import com.proyecto.ecommerce.springecommerce.service.IUsuarioService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IOrdenService ordenService;


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

        Optional<usuario> user =usuarioService.findByEmail(usuario.getEmail());
        logger.info("usuario de db: {}", user.get());



        if (user.isPresent()){
            session.setAttribute("idusuario", user.get().getId());
            if (user.get().getTipo().equals("ADMIN")){
                return "redirect:/admin";
            } else{
                return "redirect:/";

            }
        }else {
            logger.info("usuario de no existe");
        }

        return "redirect:/";
    }
    @GetMapping("/compras")
    public String obtenerCompras(Model model, HttpSession session) {
        model.addAttribute("sesion", session.getAttribute("idusuario"));;;

        usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        List<Orden> ordenes = ordenService.findByUsuario(usuario);
        model.addAttribute("ordenes", ordenes);

        return "usuario/compras";
    }
    @GetMapping("/detalle/{id}")
    public String detalleCompra(@PathVariable Integer id, HttpSession session, Model model){
        logger.info("id de la orden {}", id);

        Optional<Orden>orden = ordenService.findById(id);

        model.addAttribute("detalles", orden.get().getDetalle());

        // session
        model.addAttribute("sesion", session.getAttribute("idusuario"));

        return "usuario/detallecompra";
    }
    @GetMapping("/cerrar")
    public String cerrarSesion(HttpSession session) {
        session.removeAttribute("idusuario");
        return "redirect:/";
    }
}
