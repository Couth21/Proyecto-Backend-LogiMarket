package com.springboot.entity;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="USUARIO")
public class UsuarioEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idUsuario;
	
    @NotNull(message = "El usuario es obligatorio")
	private String usuario;
    
    @NotNull(message = "La contrasena es obligatoria")
	private String contrasena;
    
    @NotNull(message = "El email es obligatorio") 
    @Email
	private String email;
   
    @NotNull(message = "El RUC es obligatorio") 
	private int ruc;
   
    @NotNull(message = "El DNI es obligatorio") 
	private int dni;
    
    @NotNull(message = "El nombre de la empresa es obligatorio")
	private String nombreEmpresa;
    
    @NotNull(message = "La dirección es obligatoria")
	private String direccion;

    
    /*
	@NotNull(message = "El rol es obligatorio")
	@ManyToOne
	@JoinColumn(name="idRol", nullable = false)
	private RolEntity rolEntity;
	*/
	
    @ManyToOne(fetch = FetchType.EAGER, optional = false) // `optional = false` hace que sea obligatorio
    @JoinColumn(name = "id_rol", nullable = false)
    private RolEntity rolEntity;
	
	
	
	
	public UsuarioEntity(String usuario, String contrasena, String email, int ruc, String nombreEmpresa,
			String direccion, RolEntity rolEntity, int dni) {
		//super();
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.email = email;
		this.ruc = ruc;
		this.nombreEmpresa = nombreEmpresa;
		this.direccion = direccion;
		this.rolEntity = rolEntity;
		this.dni = dni;
	}
	public UsuarioEntity() {
		// TODO Auto-generated constructor stub
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
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	
	
}
