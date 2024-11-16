package com.springboot.entity;

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
@Table(name = "PRODUCTO")
public class ProductoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProducto;
	private String nombreProducto;
	private float precioBase;
	private String descripcion;
	private boolean impuestoSelectivoConsumo;
	private boolean igv;
	private String imagenProducto;
	private int stock;

	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore 
	private List<DetallePedidoEntity> detallesPedido;
	/*
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private UsuarioEntity usuarioEntity;
	*/
	
	@ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioEntity usuarioEntity; 
	

	
	
	

	public ProductoEntity(String nombreProducto, float precioBase, String descripcion, boolean impuestoSelectivoConsumo,
			boolean igv, String imagenProducto, int stock, List<DetallePedidoEntity> detallesPedido,
			UsuarioEntity usuarioEntity) {
		//super();
		this.nombreProducto = nombreProducto;
		this.precioBase = precioBase;
		this.descripcion = descripcion;
		this.impuestoSelectivoConsumo = impuestoSelectivoConsumo;
		this.igv = igv;
		this.imagenProducto = imagenProducto;
		this.stock = stock;
		this.detallesPedido = detallesPedido;
		this.usuarioEntity = usuarioEntity;
	}
	public ProductoEntity() {
		// TODO Auto-generated constructor stub
	}
	

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public float getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(float precioBase) {
		this.precioBase = precioBase;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isImpuestoSelectivoConsumo() {
		return impuestoSelectivoConsumo;
	}

	public void setImpuestoSelectivoConsumo(boolean impuestoSelectivoConsumo) {
		this.impuestoSelectivoConsumo = impuestoSelectivoConsumo;
	}

	public boolean isIgv() {
		return igv;
	}

	public void setIgv(boolean igv) {
		this.igv = igv;
	}

	public String getImagenProducto() {
		return imagenProducto;
	}

	public void setImagenProducto(String imagenProducto) {
		this.imagenProducto = imagenProducto;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public List<DetallePedidoEntity> getDetallesPedido() {
		return detallesPedido;
	}

	public void setDetallesPedido(List<DetallePedidoEntity> detallesPedido) {
		this.detallesPedido = detallesPedido;
	}

	public UsuarioEntity getUsuarioEntity() {
		return usuarioEntity;
	}

	public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}

	@JsonIgnore // Evita que este campo aparezca en la respuesta JSON
    public double getTotal() {
        return detallesPedido.stream()
                .mapToDouble(DetallePedidoEntity::getSubtotal)
                .sum();
    }
	
}