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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "SUBCATEGORIA")
public class SubcategoriaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSubCategoria;
	private String nombreSubcategoria;

	/*
	 * // 1 subcategoria solo pertenece a una categoria
	 * 
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "idCategoria") private CategoriaEntity categoriaEntity;
	 * 
	 */

	// 1 subcategoria puede tener muchos productos
	@OneToMany(mappedBy = "subcategoriaEntity")
	@JsonIgnore
	private List<ProductoEntity> productoEntity;

	@ManyToOne
	@JoinColumn(name = "idCategoria", nullable = false)
	private CategoriaEntity categoriaEntity;

	public SubcategoriaEntity(String nombreSubcategoria) {
		// super();
		this.nombreSubcategoria = nombreSubcategoria;
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

	public List<ProductoEntity> getProductoEntity() {
		return productoEntity;
	}

	public void setProductoEntity(List<ProductoEntity> productoEntity) {
		this.productoEntity = productoEntity;
	}
}
