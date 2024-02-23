package com.proyecto.ecommerce.springecommerce.controller;

import com.proyecto.ecommerce.springecommerce.model.producto;
import com.proyecto.ecommerce.springecommerce.model.usuario;
import com.proyecto.ecommerce.springecommerce.service.ProductoService;
import com.proyecto.ecommerce.springecommerce.service.UploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private UploadFileService upload;

    @Autowired
    private ProductoService productoService;

    @GetMapping("")
    public String show(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "productos/show";
    }

    @GetMapping("/create")
    public String create() {
        return "productos/create";
    }

    @PostMapping("/save")
    public String save(producto producto, @RequestParam("img") MultipartFile file  ) throws IOException {
        LOGGER.info("Este es el objeto producto {}", producto);
        usuario u = new usuario(1,"","","","","","","");
        producto.setUsuario(u);

        //imagen
        if (producto.getId()==null){ // cuando se crea un producto
            String nombreImagen = upload.saveImage(file);
            producto.setImagen(nombreImagen);
        }else {

        }
        productoService.save(producto);
        return "redirect:/productos";
    }
    @GetMapping("/edit/{id}")
    public String edit( @PathVariable Integer id, Model model){
        producto    producto = new producto();
        Optional<producto> optionalProducto = productoService.get(id);
        producto = optionalProducto.get();

        LOGGER.info("Producto buscado: {}", producto);
        model.addAttribute("producto", producto);

        return "productos/edit";
    }

    @PostMapping("/update")
    public String update(producto producto, @RequestParam("img") MultipartFile file) throws IOException {
        producto  p = new producto();
        p = productoService.get(producto.getId()).get();

        if (file.isEmpty()) { // Editamos el producto pero no cambiamos la imagen

            producto.setImagen(p.getImagen());
        } else { // cuando se edita la imagen
            // Eliminar la imagen anterior si no es la imagen por defecto
            if (!p.getImagen().equals("default.jpg")) {
                upload.deleImage(p.getImagen());
            }
            // Guardar la nueva imagen
            String nombreImagen = upload.saveImage(file);
            producto.setImagen(nombreImagen);
        }
        producto.setUsuario(p.getUsuario());
        productoService.update(producto);
        return "redirect:/productos";
    }


    @GetMapping("/delete/{id}")
    public  String delete( @PathVariable Integer id) {
        producto  p = new producto();
        p = productoService.get(id).get();
            //Eliminar cuando no sea la imagen por defecto
        if (!p.getImagen().equals("default.jpg")) {
            upload.deleImage(p.getImagen());
        }

        productoService.delete(id);
        return "redirect:/productos";
    }
}
