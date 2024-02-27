package com.proyecto.ecommerce.springecommerce.controller;

import com.proyecto.ecommerce.springecommerce.model.producto;
import com.proyecto.ecommerce.springecommerce.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class homeController {

    private  final Logger log = LoggerFactory.getLogger(homeController.class);
    @Autowired
    private ProductoService productoService;
    @GetMapping("")
    public String home (Model model) {
        model.addAttribute("productos", productoService.findAll());

        return "usuario/home";
    }
@GetMapping("productohome/{id}")
    public String productoHome( @PathVariable Integer id, Model model) {
        log.info("Id enviado como parametro {}", id);
        producto producto = new producto();
    Optional<producto> productoOptional =  productoService.get(id);
    producto = productoOptional.get();
    model.addAttribute("producto", producto);
        return  "usuario/productohome";
    }
}
