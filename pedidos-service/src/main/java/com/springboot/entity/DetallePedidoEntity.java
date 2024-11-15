package com.springboot.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="DETALLEPEDIDO")
public class DetallePedidoEntity
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idDetallePedido;
	private int cantidad;
	private float precioCompra;
	
	// Relación muchos a uno con PedidoEntity
    @ManyToOne
    @JoinColumn(name = "idPedido", nullable = false)
    private PedidoEntity pedidoEntity;
	
	
	
	public DetallePedidoEntity(int cantidad, float precioCompra) {
		//super();
		this.cantidad = cantidad;
		this.precioCompra = precioCompra;
	}
	
	
	
	
	public DetallePedidoEntity() {
		// TODO Auto-generated constructor stub
	}

	public int getIdDetallePedido() {
		return idDetallePedido;
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

	public float getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(float precioCompra) {
		this.precioCompra = precioCompra;
	}
	
}
