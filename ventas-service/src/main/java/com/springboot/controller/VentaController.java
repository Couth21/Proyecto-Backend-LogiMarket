package com.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.dto.Mensaje;
import com.springboot.dto.VentaDto;
import com.springboot.service.VentaService;

@RestController
@RequestMapping("/venta")
@CrossOrigin("¨*")
public class VentaController {
	@Autowired
	private VentaService ventaService;

	@PostMapping("/crear")
	public ResponseEntity<Mensaje> registrarVenta(@Valid @RequestBody VentaDto ventaDto) {
		try {
			VentaDto nuevaVenta = ventaService.registrarVenta(ventaDto);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new Mensaje("Venta creada exitosamente con ID: " + nuevaVenta.getIdVenta()));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(e.getMessage()));
		}
	}

	@GetMapping("/listar")
	public ResponseEntity<List<VentaDto>> listarVentas() {
		return ResponseEntity.ok(ventaService.listarVentas());
	}
}
