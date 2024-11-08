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
@Table(name = "ROL")
public class RolEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRol;
	private String rol;

	@OneToMany(mappedBy = "rolEntity", targetEntity = UsuarioEntity.class)
	@JsonIgnore
	private List<UsuarioEntity> usuarioEntity;
	
	
	

	public RolEntity(String rol, List<UsuarioEntity> usuarioEntity) {
		super();
		this.rol = rol;
		this.usuarioEntity = usuarioEntity;
	}

	public RolEntity() {
		// TODO Auto-generated constructor stub
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public List<UsuarioEntity> getUsuarioEntity() {
		return usuarioEntity;
	}

	public void setUsuarioEntity(List<UsuarioEntity> usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}
	
	

}
