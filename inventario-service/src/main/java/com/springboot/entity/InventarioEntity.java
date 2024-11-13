package com.springboot.entity;

import java.sql.Date;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "INVENTARIO")
public class InventarioEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idInventario;
	private int cantidadDisponible;
	private float precioPersonalizado;
	private Date fechaVencimiento;
	private String estadoProducto;



	@ManyToOne
	@JoinColumn(name = "idProducto")
	private ProductoEntity productoEntity;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private UsuarioEntity usuarioEntity;

	public InventarioEntity(int cantidadDisponible, float precioPersonalizado, Date fechaVencimiento,
			String estadoProducto, int stock, ProductoEntity productoEntity, UsuarioEntity usuarioEntity) {
		super();
		this.cantidadDisponible = cantidadDisponible;
		this.precioPersonalizado = precioPersonalizado;
		this.fechaVencimiento = fechaVencimiento;
		this.estadoProducto = estadoProducto;

		this.productoEntity = productoEntity;
		this.usuarioEntity = usuarioEntity;
	}

	public InventarioEntity() {
		// TODO Auto-generated constructor stub
	}

	public int getIdInventario() {
		return idInventario;
	}

	public void setIdInventario(int idInventario) {
		this.idInventario = idInventario;
	}

	public int getCantidadDisponible() {
		return cantidadDisponible;
	}

	public void setCantidadDisponible(int cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}

	public float getPrecioPersonalizado() {
		return precioPersonalizado;
	}

	public void setPrecioPersonalizado(float precioPersonalizado) {
		this.precioPersonalizado = precioPersonalizado;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getEstadoProducto() {
		return estadoProducto;
	}

	public void setEstadoProducto(String estadoProducto) {
		this.estadoProducto = estadoProducto;
	}

	public ProductoEntity getProductoEntity() {
		return productoEntity;
	}

	public void setProductoEntity(ProductoEntity productoEntity) {
		this.productoEntity = productoEntity;
	}

	public UsuarioEntity getUsuarioEntity() {
		return usuarioEntity;
	}

	public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}

}
