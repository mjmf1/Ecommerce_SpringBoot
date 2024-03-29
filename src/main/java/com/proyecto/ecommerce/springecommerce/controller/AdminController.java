package com.proyecto.ecommerce.springecommerce.controller;

import com.proyecto.ecommerce.springecommerce.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.proyecto.ecommerce.springecommerce.model.producto;
import com.proyecto.ecommerce.springecommerce.service.ProductoService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ProductoService productoService;

    @GetMapping("")
    public String home( Model model) {
        List<producto> productos = productoService.findAll();
        model.addAttribute("productos", productos);

        return "admin/home";
    }
}

