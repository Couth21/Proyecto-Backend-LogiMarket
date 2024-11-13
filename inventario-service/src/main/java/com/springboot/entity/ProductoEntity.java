package com.springboot.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	private int stock;

	
	

	/*
	//1 producto solo pertenece a una subcategoria
	@OneToOne
	@JoinColumn(name="idSubcategoria")
	private SubcategoriaEntity subcategoriaEntity;
	_*/





	
	
	
	//1 producto puede tener múltiples inventarios
	@OneToMany(mappedBy = "productoEntity", targetEntity = InventarioEntity.class)
	@JsonIgnore
	private List<InventarioEntity> inventarioEntity;
	
	/*
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "productos"}) // Evita referencias cíclicas y problemas de proxy
	private UsuarioEntity usuarioEntity;
	
	@ManyToOne
	@JoinColumn(name = "idSubcategoria", nullable = false)
	private SubcategoriaEntity subcategoria;
	*/
	
	
	@ManyToOne
	@JoinColumn(name = "id_subcategoria")
	private SubcategoriaEntity subcategoria;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private UsuarioEntity usuarioEntity;

	
	

	
	

	public ProductoEntity(String nombreProducto, float precioBase, String descripcion, boolean impuestoSelectivoConsumo,
			boolean igv, String imagenProducto, int stock, List<InventarioEntity> inventarioEntity,
			SubcategoriaEntity subcategoria, UsuarioEntity usuarioEntity) {
		//super();
		this.nombreProducto = nombreProducto;
		this.precioBase = precioBase;
		this.descripcion = descripcion;
		this.impuestoSelectivoConsumo = impuestoSelectivoConsumo;
		this.igv = igv;
		this.imagenProducto = imagenProducto;
		this.stock = stock;
		this.inventarioEntity = inventarioEntity;
		this.subcategoria = subcategoria;
		this.usuarioEntity = usuarioEntity;
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

	

	public List<InventarioEntity> getInventarioEntity() {
		return inventarioEntity;
	}

	public void setInventarioEntity(List<InventarioEntity> inventarioEntity) {
		this.inventarioEntity = inventarioEntity;
	}

	public SubcategoriaEntity getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(SubcategoriaEntity subcategoria) {
		this.subcategoria = subcategoria;
	}

	public UsuarioEntity getUsuarioEntity() {
		return usuarioEntity;
	}

	public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	
	
}
