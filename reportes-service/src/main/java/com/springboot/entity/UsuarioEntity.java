package com.springboot.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
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
	private String contraseña;    
	private String email;    
	private int ruc;    
	private int dni;    
	private String nombreEmpresa;
	private String direccion;
	
	@OneToMany(mappedBy = "usuarioEntity", cascade = CascadeType.ALL, targetEntity = ReporteEntity.class)
	private List<ReporteEntity> reporteEntity;


	public UsuarioEntity(String usuario, String contraseña, String email, int ruc, int dni, String nombreEmpresa,
			String direccion, List<ReporteEntity> reporteEntity) {
		//super();
		this.usuario = usuario;
		this.contraseña = contraseña;
		this.email = email;
		this.ruc = ruc;
		this.dni = dni;
		this.nombreEmpresa = nombreEmpresa;
		this.direccion = direccion;
		this.reporteEntity = reporteEntity;
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

	public List<ReporteEntity> getReporteEntity() {
		return reporteEntity;
	}

	public void setReporteEntity(List<ReporteEntity> reporteEntity) {
		this.reporteEntity = reporteEntity;
	}
	
	
	
	
	

	
}