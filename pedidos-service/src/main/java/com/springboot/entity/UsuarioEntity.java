package com.springboot.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private String contrasena;    
	private String email;    
	private int ruc;    
	private int dni;    
	private String nombreEmpresa;
	private String direccion;
	
	
	/*
	@OneToMany(mappedBy = "usuarioEntity", cascade = CascadeType.ALL, targetEntity = PedidoEntity.class)
	private List<PedidoEntity> pedidoEntity;
	*/


	@OneToMany(mappedBy = "usuarioEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // Evita la serialización recursiva de los pedidos en Usuario
    private List<PedidoEntity> pedidos;

    /*
    @OneToMany(mappedBy = "usuarioEntity", targetEntity = ProductoEntity.class)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "usuarioEntity"}) // Evita ciclos de serialización
	private List<ProductoEntity> productos;
	*/
	
	@OneToMany(mappedBy = "usuarioEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // Evita la serialización recursiva de los pedidos en Usuario
    private List<ProductoEntity> productos; 
	
	

	

	public UsuarioEntity(String usuario, String contrasena, String email, int ruc, int dni, String nombreEmpresa,
			String direccion, List<PedidoEntity> pedidos, List<ProductoEntity> productos) {
		//super();
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.email = email;
		this.ruc = ruc;
		this.dni = dni;
		this.nombreEmpresa = nombreEmpresa;
		this.direccion = direccion;
		this.pedidos = pedidos;
		this.productos = productos;
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

	public List<PedidoEntity> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<PedidoEntity> pedidos) {
		this.pedidos = pedidos;
	}

	public List<ProductoEntity> getProductos() {
		return productos;
	}

	public void setProductos(List<ProductoEntity> productos) {
		this.productos = productos;
	}


	
}
