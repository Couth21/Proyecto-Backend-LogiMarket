package com.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="DETALLEVENTA")
public class DetalleVentaEntity
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idDetalleVenta;
    private Integer cantidad;
    private Double precioVenta;

    //muchos detalles de venta solo pueden terner una venta
    @ManyToOne
    @JoinColumn(name = "idVenta")
    private VentaEntity ventaEntity;

	
    
    
    public DetalleVentaEntity(Integer cantidad, Double precioVenta, VentaEntity ventaEntity) {
		//super();
		this.cantidad = cantidad;
		this.precioVenta = precioVenta;
		this.ventaEntity = ventaEntity;
	}
	
	public DetalleVentaEntity() {
		// TODO Auto-generated constructor stub
	}

	public Long getIdDetalleVenta() {
		return idDetalleVenta;
	}

	public void setIdDetalleVenta(Long idDetalleVenta) {
		this.idDetalleVenta = idDetalleVenta;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(Double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public VentaEntity getVentaEntity() {
		return ventaEntity;
	}

	public void setVentaEntity(VentaEntity ventaEntity) {
		this.ventaEntity = ventaEntity;
	}
	
	
	
    
    
	
}
