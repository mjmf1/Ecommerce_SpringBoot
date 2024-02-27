package com.proyecto.ecommerce.springecommerce.controller;

import com.proyecto.ecommerce.springecommerce.model.DetalleOrden;
import com.proyecto.ecommerce.springecommerce.model.Orden;
import com.proyecto.ecommerce.springecommerce.model.producto;
import com.proyecto.ecommerce.springecommerce.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class homeController {

    private  final Logger log = LoggerFactory.getLogger(homeController.class);
    @Autowired
    private ProductoService productoService;

    //Esto es para almacenar los detalles de la orden
    List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();

    //Datos de la orden
    Orden orden = new Orden();

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

    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad){
        DetalleOrden detalleOrden = new DetalleOrden();
        producto producto = new producto();
        double sumaTotal = 0;
        Optional<producto> optionalProducto = productoService.get(id);
        log.info("Producto añadido: {})", optionalProducto.get());
        log.info("Cantidad {}", cantidad);

        return "usuario/carrito";
    }
}
