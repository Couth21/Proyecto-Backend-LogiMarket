package com.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.springboot.entity.ReporteEntity;
import com.springboot.entity.UsuarioEntity;
import com.springboot.repository.ReporteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReporteService 
{
	private final ReporteRepository reporteRepository;
	
	
	@Autowired
    private RestTemplate restTemplate;
	
	
	public UsuarioEntity getReporteEntity(int idUsuario) {
        String url = "http://localhost:9091/usuario/detail/" + idUsuario;
        try {
            return restTemplate.getForObject(url, UsuarioEntity.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return null;
            }
            throw e;
        }
    }


	public ReporteService(ReporteRepository reporteRepository) {
		this.reporteRepository = reporteRepository;
	}

	public List<ReporteEntity> obtenerTodosLosReportes() {
		return reporteRepository.findAll();
	}

	public ReporteEntity guardarReporte(ReporteEntity reporte) {
		return reporteRepository.save(reporte);
	}

	public List<ReporteEntity> obtenerReportesPorAnio(Integer anio) {
		return reporteRepository.findByAnio(anio);
	}

	public List<ReporteEntity> obtenerReportesPorMesYAnio(ReporteEntity.Mes mes, Integer anio) {
		return reporteRepository.findByMesAndAnio(mes, anio);
	}
}