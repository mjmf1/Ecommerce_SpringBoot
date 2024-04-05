package com.proyecto.ecommerce.springecommerce.service;

import com.proyecto.ecommerce.springecommerce.model.Orden;
import com.proyecto.ecommerce.springecommerce.model.usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.ecommerce.springecommerce.repository.IOrdenRepository;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenServiceImpl implements IOrdenService {
    @Autowired
    private IOrdenRepository ordenRepository;

    @Override
    public Orden save(Orden orden) {
        return ordenRepository.save(orden);
    }

    @Override
    public List<Orden> findAll() {
        return ordenRepository.findAll();
    }

    @Override
    public Optional<Orden> findById(Integer id) {
        return ordenRepository.findById(id);
    }

    public String generarNumeroOrden(){
        int numero = 0;
        String numeroConcantenado = "";

        List<Orden> ordenes = findAll();

        List<Integer> numeros = new ArrayList<Integer>();

        ordenes.stream().forEach(o->numeros.add(Integer.parseInt(o.getNumero())));

        if (ordenes.isEmpty()){
            numero = 1;
        }else{
            numero = numeros.stream().max(Integer::compare).get();
            numero++;
        }

        if (numero<10) {
            numeroConcantenado = "000000000" + String.valueOf(numero);
        }else if(numero<100){
            numeroConcantenado = "00000000" + String.valueOf(numero);
        }else if(numero<1000){
            numeroConcantenado = "0000000" + String.valueOf(numero);
        }else if(numero<10000){
            numeroConcantenado = "000000" + String.valueOf(numero);
        }

        return numeroConcantenado;
    }

    @Override
    public List<Orden> findByUsuario(usuario usuario) {
        return ordenRepository.findByUsuario(usuario);
    }
}
