package com.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.ReporteEntity;

public interface ReporteRepository extends JpaRepository<ReporteEntity, Long> {
    List<ReporteEntity> findByAnio(Integer anio);
    List<ReporteEntity> findByMesAndAnio(ReporteEntity.Mes mes, Integer anio);
    
    List<ReporteEntity> findByUsuarioEntityIdUsuario(long idUsuario); // Solo este método
}