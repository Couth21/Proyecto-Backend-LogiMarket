package com.springboot.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UsuarioDto 
{
	@NotNull(message = "El usuario es obligatorio")
	private String usuario;
	@NotNull(message = "La contraseña es obligatoria")
	private String contraseña;
	@NotNull(message = "El email es obligatorio")
	@Email(message = "Debe ser un email válido")
	private String email;
	private int ruc;
	private int dni;
	private String nombreEmpresa;
	private String direccion;
	
	
	private int idRol;
	private String rol;
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
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
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public UsuarioDto(String usuario, String contraseña, String email, int ruc, String nombreEmpresa,
			String direccion, int dni) {
		//super();
		this.usuario = usuario;
		this.contraseña = contraseña;
		this.email = email;
		this.ruc = ruc;
		this.nombreEmpresa = nombreEmpresa;
		this.direccion = direccion;
		this.dni = dni;
	}
	
	public UsuarioDto() {
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
	
	
	
}

