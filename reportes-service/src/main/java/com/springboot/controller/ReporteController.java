package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.entity.ReporteEntity;
import com.springboot.service.ReporteService;

@RestController
@RequestMapping("/reporte")
@CrossOrigin("*")
public class ReporteController 
{
	@Autowired
	
	private final ReporteService reporteService;


    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping
    public List<ReporteEntity> obtenerTodosLosReportes() {
        return reporteService.obtenerTodosLosReportes();
    }

    @PostMapping
    public ReporteEntity guardarReporte(@RequestBody ReporteEntity reporte) {
        return reporteService.guardarReporte(reporte);
    }

    @GetMapping("/anio/{anio}")
    public List<ReporteEntity> obtenerReportesPorAnio(@PathVariable Integer anio) {
        return reporteService.obtenerReportesPorAnio(anio);
    }

    @GetMapping("/mes/{mes}/anio/{anio}")
    public List<ReporteEntity> obtenerReportesPorMesYAnio(@PathVariable ReporteEntity.Mes mes, @PathVariable Integer anio) {
        return reporteService.obtenerReportesPorMesYAnio(mes, anio);
    }
}

