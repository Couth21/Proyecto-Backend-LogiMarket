package com.springboot.controller;

import java.util.List;


import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.dto.FacturaProducto;
import com.springboot.service.FacturaProductoService;


@RestController
@RequestMapping("/api/facturas")
public class FacturaProductoController {

    private final FacturaProductoService facturaProductoService;

    public FacturaProductoController(FacturaProductoService facturaProductoService) {
        this.facturaProductoService = facturaProductoService;
    }

    @GetMapping
    public List<FacturaProducto> listarFacturas() {
        return facturaProductoService.listarFacturas();
    }
}