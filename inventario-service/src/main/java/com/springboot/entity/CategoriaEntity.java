package com.springboot.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "CATEGORIA")
public class CategoriaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCategoria;
	private String categoria;

	// 1 categoria puede tener muchas subcategorias
	@OneToMany(mappedBy = "categoriaEntity")
	@JsonIgnore
	private List<SubcategoriaEntity> subcategoriaEntity;

	public CategoriaEntity(String categoria) {
		// super();
		this.categoria = categoria;
	}

	public CategoriaEntity() {
		// TODO Auto-generated constructor stub
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public List<SubcategoriaEntity> getSubcategoriaEntity() {
		return subcategoriaEntity;
	}

	public void setSubcategoriaEntity(List<SubcategoriaEntity> subcategoriaEntity) {
		this.subcategoriaEntity = subcategoriaEntity;
	}

}
