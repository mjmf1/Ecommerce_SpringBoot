package com.proyecto.ecommerce.springecommerce.controller;

import com.proyecto.ecommerce.springecommerce.model.DetalleOrden;
import com.proyecto.ecommerce.springecommerce.model.Orden;
import com.proyecto.ecommerce.springecommerce.model.producto;
import com.proyecto.ecommerce.springecommerce.model.usuario;
import com.proyecto.ecommerce.springecommerce.service.IDetalleOrdenService;
import com.proyecto.ecommerce.springecommerce.service.IOrdenService;
import com.proyecto.ecommerce.springecommerce.service.IUsuarioService;
import com.proyecto.ecommerce.springecommerce.service.ProductoService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class homeController {

    private  final Logger log = LoggerFactory.getLogger(homeController.class);
    @Autowired
    private ProductoService productoService;
    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IOrdenService ordenService;

    @Autowired
    IDetalleOrdenService detalleOrdenService;


    //Esto es para almacenar los detalles de la orden
    List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();

    //Datos de la orden
    Orden orden = new Orden();

    @GetMapping("")
    public String home (Model model, HttpSession session) {
        log.info("Sesion del usuario {}", session.getAttribute("idusuario"));

        model.addAttribute("productos", productoService.findAll());

        //Session
        model.addAttribute("sesion", session.getAttribute("idusuario"));

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
    public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {
        DetalleOrden detalleOrden = new DetalleOrden();
        producto producto = new producto();
        double sumaTotal = 0;
        Optional<producto> optionalProducto = productoService.get(id);
        log.info("Producto añadido: {})", optionalProducto.get());
        log.info("Cantidad {}", cantidad);
        producto = optionalProducto.get();

        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setTotal(producto.getPrecio() * cantidad);
        detalleOrden.setProducto(producto);

        //Validar que el producto no se añada 2 veces
        Integer idProducto = producto.getId();
        boolean ingresado = detalles.stream().anyMatch(p->p.getProducto().getId() == idProducto);

        if (!ingresado){
            detalles.add(detalleOrden);
        }



        sumaTotal = detalles.stream().mapToDouble(dt->dt.getTotal()).sum();

        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);

        return "usuario/carrito";
    }

    // Quitar productos del carrito

    @GetMapping("/delete/cart/{id}")
    public String deleteProductoCart(@PathVariable Integer id, Model model ){
        //Lista nueva de productos
        List<DetalleOrden> ordenesNuevas = new ArrayList<DetalleOrden>();
        for (DetalleOrden detalleOrden : detalles){
            if (detalleOrden.getProducto().getId()!=id){
                ordenesNuevas.add(detalleOrden);
            }
        }
        //Poner la nueva lista con los productos restantes
        detalles = ordenesNuevas;
        double sumaTotal = 0;
        sumaTotal = detalles.stream().mapToDouble(dt->dt.getTotal()).sum();

        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);


        return "usuario/carrito";
    }

    @GetMapping("/getCart")
    public String getCart( Model model , HttpSession session) {
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);

        //Sesion
        model.addAttribute("sesion", session.getAttribute("idusuario"));

        return "usuario/carrito";    }

    @GetMapping("/order")
    public String order( Model model, HttpSession session){

        usuario usuario = usuarioService.findById( Integer.parseInt(session.getAttribute("idusuario").toString())).get();

        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        model.addAttribute("usuario", usuario);

        return "usuario/resumenorden";
    }
    //Guardar la orden
    @GetMapping("/saveOrder")
    public String saveOrder( HttpSession session){
        Date fechaCreacion = new Date();
        orden.setFechaCreacion(fechaCreacion);
        orden.setNumero(ordenService.generarNumeroOrden());

        //Usuario
        usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();

        orden.setUsuario(usuario);
        ordenService.save(orden);

        //Guardar Detalles
        for (DetalleOrden dt:detalles) {
            dt.setOrden(orden);
            detalleOrdenService.save(dt);

        }
        //Limpiar lista y orden

        orden = new Orden();
        detalles.clear();

        return "redirect:/";

    }
    @PostMapping("/search")
    public String searchProduct(@RequestParam String nombre, Model model){
        log.info("Nombre del producto {}:", nombre);

        // Convertir el nombre de búsqueda a minúsculas
        String nombreBusqueda = nombre.toLowerCase();

        // Obtener todos los productos y filtrarlos
        List<producto> productos = productoService.findAll().stream().filter(p->p.getNombre().toLowerCase().contains(nombreBusqueda)).collect(Collectors.toList());
        model.addAttribute("productos", productos);
        return  "usuario/home";
    }
}
