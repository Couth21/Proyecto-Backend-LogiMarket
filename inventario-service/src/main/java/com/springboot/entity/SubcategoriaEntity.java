package com.springboot.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "SUBCATEGORIA")
public class SubcategoriaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSubCategoria;
	private String nombreSubcategoria;

	
	
	@ManyToOne
	@JoinColumn(name = "idCategoria", nullable = false)
	private CategoriaEntity categoriaEntity;
	
	
	

	@OneToMany(mappedBy = "subcategoria", cascade = CascadeType.ALL) // Cambiado a "subcategoria"
	@JsonIgnore
	private List<ProductoEntity> productos = new ArrayList<>();

	
	
	
	
	

	public SubcategoriaEntity(String nombreSubcategoria, CategoriaEntity categoriaEntity,
			List<ProductoEntity> productos) {
		//super();
		this.nombreSubcategoria = nombreSubcategoria;
		this.categoriaEntity = categoriaEntity;
		this.productos = productos;
	}

	public SubcategoriaEntity() {
		// TODO Auto-generated constructor stub
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

	public CategoriaEntity getCategoriaEntity() {
		return categoriaEntity; // Retorna el objeto CategoriaEntity asociado
	}

	public void setCategoriaEntity(CategoriaEntity categoriaEntity) {
		this.categoriaEntity = categoriaEntity; // Asigna un objeto CategoriaEntity
	}

	public List<ProductoEntity> getProductos() {
		return productos;
	}

	public void setProductos(List<ProductoEntity> productos) {
		this.productos = productos;
	}


}
