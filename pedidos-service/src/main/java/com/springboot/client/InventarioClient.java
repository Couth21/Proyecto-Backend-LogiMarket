package com.springboot.client;

import org.springframework.cloud.openfeign.FeignClient;
import com.springboot.entity.ProductoEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventario-service", url = "http://localhost:9092/inventario")
public interface InventarioClient {

	@GetMapping("/producto/{idProducto}")
    ProductoEntity obtenerProductoPorId(@PathVariable("idProducto") int idProducto);
}