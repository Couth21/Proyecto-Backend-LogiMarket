package com.springboot.entity;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="USUARIO")
public class UsuarioEntity 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idUsuario;    
	private String usuario;
	private String contrasena;    
	private String email;    
	private int ruc;    
	private int dni;    
	private String nombreEmpresa;
	private String direccion;
	
	//1 usuario puede tener varios inventarios
	@OneToMany(mappedBy = "usuarioEntity", targetEntity = InventarioEntity.class)
	@JsonIgnore
	private List<InventarioEntity> inventarioEntity;
	
	@OneToMany(mappedBy = "usuarioEntity", targetEntity = ProductoEntity.class)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "usuarioEntity"}) // Evita ciclos de serialización
	private List<ProductoEntity> productos;


	
	
	

	

	
	
	public UsuarioEntity(String usuario, String contrasena, String email, int ruc, int dni, String nombreEmpresa,
			String direccion, List<InventarioEntity> inventarioEntity) {
		//super();
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.email = email;
		this.ruc = ruc;
		this.dni = dni;
		this.nombreEmpresa = nombreEmpresa;
		this.direccion = direccion;
		this.inventarioEntity = inventarioEntity;
	}


	public UsuarioEntity() {
		// TODO Auto-generated constructor stub
	}


	public long getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getContrasena() {
		return contrasena;
	}


	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getRuc() {
		return ruc;
	}


	public void setRuc(int ruc) {
		this.ruc = ruc;
	}


	public int getDni() {
		return dni;
	}


	public void setDni(int dni) {
		this.dni = dni;
	}


	public String getNombreEmpresa() {
		return nombreEmpresa;
	}


	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public List<InventarioEntity> getInventarioEntity() {
		return inventarioEntity;
	}


	public void setInventarioEntity(List<InventarioEntity> inventarioEntity) {
		this.inventarioEntity = inventarioEntity;
	}

}

