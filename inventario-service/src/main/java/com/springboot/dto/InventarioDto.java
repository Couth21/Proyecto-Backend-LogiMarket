package com.springboot.dto;

import java.sql.Date;





public class InventarioDto 
{
	private int cantidadDisponible;
	private float precioPersonalizado;
	private Date fechaVencimiento;
	private String estadoProducto;
	


	
	
	// Detalles del producto
	private int idProducto;
	private String nombreProducto;
    private float precioBase;
    private String descripcion;
    private boolean impuestoSelectivoConsumo;
    private boolean igv;
    private String imagenProducto;

	
    
    // Detalles de la subcategoría
    private int idSubCategoria;
    private String nombreSubcategoria;
    
    // Detalles de la categoría
    private int idCategoria;
    private String nombreCategoria;
    
   
 // Agrega detalles del usuario y rol si es necesario
    private String nombreUsuario;
    private String nombreRol;

    
    
    //constructor
    
	
	
	public InventarioDto() {
		// TODO Auto-generated constructor stub
	}

	

	public InventarioDto(int cantidadDisponible, float precioPersonalizado, Date fechaVencimiento,
			String estadoProducto, int idProducto, String nombreProducto, float precioBase, String descripcion,
			boolean impuestoSelectivoConsumo, boolean igv, String imagenProducto, int stock, int idSubCategoria,
			String nombreSubcategoria, int idCategoria, String nombreCategoria, String nombreUsuario,
			String nombreRol) {
		//super();
		this.cantidadDisponible = cantidadDisponible;
		this.precioPersonalizado = precioPersonalizado;
		this.fechaVencimiento = fechaVencimiento;
		this.estadoProducto = estadoProducto;
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.precioBase = precioBase;
		this.descripcion = descripcion;
		this.impuestoSelectivoConsumo = impuestoSelectivoConsumo;
		this.igv = igv;
		this.imagenProducto = imagenProducto;

		this.idSubCategoria = idSubCategoria;
		this.nombreSubcategoria = nombreSubcategoria;
		this.idCategoria = idCategoria;
		this.nombreCategoria = nombreCategoria;
		this.nombreUsuario = nombreUsuario;
		this.nombreRol = nombreRol;
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


	public int getIdProducto() {
		return idProducto;
	}


	public void setIdProducto(int idProductto) {
		this.idProducto = idProductto;
	}


	public String getNombreProducto() {
		return nombreProducto;
	}


	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}


	public float getPrecioBase() {
		return precioBase;
	}


	public void setPrecioBase(float precioBase) {
		this.precioBase = precioBase;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public boolean isImpuestoSelectivoConsumo() {
		return impuestoSelectivoConsumo;
	}


	public void setImpuestoSelectivoConsumo(boolean impuestoSelectivoConsumo) {
		this.impuestoSelectivoConsumo = impuestoSelectivoConsumo;
	}


	public boolean isIgv() {
		return igv;
	}


	public void setIgv(boolean igv) {
		this.igv = igv;
	}


	public String getImagenProducto() {
		return imagenProducto;
	}


	public void setImagenProducto(String imagenProducto) {
		this.imagenProducto = imagenProducto;
	}


	public int getIdSubCategoria() {
		return idSubCategoria;
	}


	public void setIdSubCategoria(int idSubCategoria) {
		this.idSubCategoria = idSubCategoria;
	}


	public String getNombreSubcategoria() {
		return nombreSubcategoria;
	}


	public void setNombreSubcategoria(String nombreSubcategoria) {
		this.nombreSubcategoria = nombreSubcategoria;
	}


	public int getIdCategoria() {
		return idCategoria;
	}


	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}


	public String getNombreCategoria() {
		return nombreCategoria;
	}


	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}



	


	
	
	
	
	
}

