package com.proyecto.ecommerce.springecommerce.repository;

import com.proyecto.ecommerce.springecommerce.model.DetalleOrden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDetalleOrdenRepository extends JpaRepository <DetalleOrden, Integer>{

}
