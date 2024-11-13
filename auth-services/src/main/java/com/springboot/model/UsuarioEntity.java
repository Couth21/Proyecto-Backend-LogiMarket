package com.springboot.model;

public class UsuarioEntity {
	 private long idUsuario;

	    private String usuario;

	    private String contrasena;

	    private String email;

	    private int ruc;

	    private int dni;

	    private String nombreEmpresa;

	    private String direccion;

	    private RolEntity rolEntity;  // Relación con RolEntity

	    // Constructor vacío
	    public UsuarioEntity() {}

	    // Getters y Setters
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

	    public RolEntity getRolEntity() {
	        return rolEntity;
	    }

	    public void setRolEntity(RolEntity rolEntity) {
	        this.rolEntity = rolEntity;
	    }
}
