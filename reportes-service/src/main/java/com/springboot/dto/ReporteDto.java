package com.springboot.dto;

import java.math.BigDecimal;

import com.springboot.entity.ReporteEntity.Mes;

public class ReporteDto 
{
	private Mes mes;
	private Integer anio;
	private BigDecimal ventasTotales;
	
	public ReporteDto(Mes mes, Integer anio, BigDecimal ventasTotales) {
		//super();
		this.mes = mes;
		this.anio = anio;
		this.ventasTotales = ventasTotales;
	}
	
	public ReporteDto() {
		// TODO Auto-generated constructor stub
	}

	public Mes getMes() {
		return mes;
	}

	public void setMes(Mes mes) {
		this.mes = mes;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public BigDecimal getVentasTotales() {
		return ventasTotales;
	}

	public void setVentasTotales(BigDecimal ventasTotales) {
		this.ventasTotales = ventasTotales;
	}
	
	
	
	
}
