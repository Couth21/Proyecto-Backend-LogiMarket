package com.springboot.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="PRODUCTO")
public class ProductoEntity 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idProducto;
	private String nombreProducto;
	private float precioBase; //precio del producto establecido por el proveedor
	private String descripcion;
	private boolean impuestoSelectivoConsumo;
	private boolean igv;
	private String imagenProducto;
	
	

	
	//1 producto solo pertenece a una subcategoria
	@OneToOne
	@JoinColumn(name="idSubcategoria")
	private SubcategoriaEntity subcategoriaEntity;
	
	//1 producto puede tener múltiples inventarios
	@OneToMany(mappedBy = "productoEntity", targetEntity = InventarioEntity.class)
	@JsonIgnore
	private List<InventarioEntity> inventarioEntity;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	private UsuarioEntity usuarioEntity;

	
	
	
	public ProductoEntity(String nombreProducto, float precioBase, String descripcion, boolean impuestoSelectivoConsumo,
			boolean igv, String imagenProducto, SubcategoriaEntity subcategoriaEntity,
			List<InventarioEntity> inventarioEntity) {
		//super();
		this.nombreProducto = nombreProducto;
		this.precioBase = precioBase;
		this.descripcion = descripcion;
		this.impuestoSelectivoConsumo = impuestoSelectivoConsumo;
		this.igv = igv;
		this.imagenProducto = imagenProducto;
		this.subcategoriaEntity = subcategoriaEntity;
		this.inventarioEntity = inventarioEntity;
	}

	
	public ProductoEntity() {
		// TODO Auto-generated constructor stub
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
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

	public SubcategoriaEntity getSubcategoriaEntity() {
		return subcategoriaEntity;
	}

	public void setSubcategoriaEntity(SubcategoriaEntity subcategoriaEntity) {
		this.subcategoriaEntity = subcategoriaEntity;
	}

	public List<InventarioEntity> getInventarioEntity() {
		return inventarioEntity;
	}

	public void setInventarioEntity(List<InventarioEntity> inventarioEntity) {
		this.inventarioEntity = inventarioEntity;
	}


	
}