package com.proyecto.ecommerce.springecommerce.controller;

import com.proyecto.ecommerce.springecommerce.model.Orden;
import com.proyecto.ecommerce.springecommerce.service.IOrdenService;
import com.proyecto.ecommerce.springecommerce.service.IUsuarioService;
import com.proyecto.ecommerce.springecommerce.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.proyecto.ecommerce.springecommerce.model.producto;


import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ProductoService productoService;

    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private IOrdenService ordenService;

    private Logger logg = LoggerFactory.getLogger(AdminController.class);

    @GetMapping("")
    public String home( Model model) {
        List<producto> productos = productoService.findAll();
        model.addAttribute("productos", productos);

        return "admin/home";
    }
    @GetMapping("/usuarios")
    public String usuarios( Model model){
        model.addAttribute("usuarios", usuarioService.findAll());
        return "admin/usuarios";
    }
    @GetMapping("/ordenes")
    public String ordenes(Model model) {
        model.addAttribute("ordenes", ordenService.findAll());
        return "admin/ordenes";
    }
    @GetMapping("/detalle/{id}")
    public String detalle(Model model, @PathVariable Integer id) {
        logg.info("Id de la orden {}", id );
        Orden orden =ordenService.findById(id).get();
        model.addAttribute("detalles", orden.getDetalle());
        return "admin/detalleOrden";
    }
}

