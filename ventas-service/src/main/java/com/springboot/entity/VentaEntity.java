package com.springboot.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="VENTA")
public class VentaEntity 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;
    private Date fechaVenta;
    private Double totalVenta;

    //1 venta tiene muchos detallesdeventa
    @OneToMany(mappedBy = "ventaEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVentaEntity> detallesVentaEntity;

    
    
	public VentaEntity(Date fechaVenta, Double totalVenta, List<DetalleVentaEntity> detallesVentaEntity) {
		//super();
		this.fechaVenta = fechaVenta;
		this.totalVenta = totalVenta;
		this.detallesVentaEntity = detallesVentaEntity;
	}
	
	public VentaEntity() {
		// TODO Auto-generated constructor stub
	}

	public Long getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Long idVenta) {
		this.idVenta = idVenta;
	}

	public Date getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public Double getTotalVenta() {
		return totalVenta;
	}

	public void setTotalVenta(Double totalVenta) {
		this.totalVenta = totalVenta;
	}

	public List<DetalleVentaEntity> getDetallesVentaEntity() {
		return detallesVentaEntity;
	}

	public void setDetallesVentaEntity(List<DetalleVentaEntity> detallesVentaEntity) {
		this.detallesVentaEntity = detallesVentaEntity;
	}
    
	
    
    

}
