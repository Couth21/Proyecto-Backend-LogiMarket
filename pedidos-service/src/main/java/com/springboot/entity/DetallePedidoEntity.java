package com.springboot.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "DETALLEPEDIDO")
public class DetallePedidoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDetallePedido;
	private int cantidad;

	

	
	@ManyToOne
    @JoinColumn(name = "idProducto", nullable = false)
    private ProductoEntity producto;

    @ManyToOne
    @JoinColumn(name = "idPedido", nullable = false)
    @JsonBackReference
    private PedidoEntity pedido;


	
	
	public DetallePedidoEntity(int cantidad, PedidoEntity pedido, ProductoEntity producto) {
		this.cantidad = cantidad;
		this.pedido = pedido;
		this.producto = producto;
	}

	public DetallePedidoEntity() {
		// Default constructor
	}


	public void setIdDetallePedido(int idDetallePedido) {
		this.idDetallePedido = idDetallePedido;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public PedidoEntity getPedido() {
		return pedido;
	}

	public void setPedido(PedidoEntity pedido) {
		this.pedido = pedido;
	}

	public ProductoEntity getProducto() {
		return producto;
	}

	public void setProducto(ProductoEntity producto) {
		this.producto = producto;
	}


	public void setCantidad(Integer cantidad) {
	    this.cantidad = cantidad;
	}


	public Integer getIdDetallePedido() {
	    return idDetallePedido;
	}

	public void setIdDetallePedido(Integer idDetallePedido) {
	    this.idDetallePedido = idDetallePedido;
	}
    
    
    
	@Transient
	public double getSubtotal() {
	    BigDecimal subtotal = BigDecimal.valueOf(cantidad)
	            .multiply(BigDecimal.valueOf(producto.getPrecioBase()))
	            .setScale(2, RoundingMode.HALF_UP); // Redondea a 2 decimales
	    return subtotal.doubleValue();
	}
	
}