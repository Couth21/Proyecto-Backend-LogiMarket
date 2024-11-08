package com.springboot.entity;

import java.sql.Date;
import java.util.List;

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
@Table(name="PEDIDO")
public class PedidoEntity 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPedido;
	private Date fechaPedido;
	private Date fechaEntrega;
	private String estadoPedido;
	
	// Relación uno a muchos con DetallePedidoEntity
    @OneToMany(mappedBy = "pedidoEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedidoEntity> detallePedidoEntities;
    
    @ManyToOne
	@JoinColumn(name = "id_usuario")
	private UsuarioEntity usuarioEntity;
	
	
	
	




	public PedidoEntity(Date fechaPedido, Date fechaEntrega, String estadoPedido,
			List<DetallePedidoEntity> detallePedidoEntities, UsuarioEntity usuarioEntity) {
		super();
		this.fechaPedido = fechaPedido;
		this.fechaEntrega = fechaEntrega;
		this.estadoPedido = estadoPedido;
		this.detallePedidoEntities = detallePedidoEntities;
		this.usuarioEntity = usuarioEntity;
	}




	public PedidoEntity() {
		// TODO Auto-generated constructor stub
	}

	
	
	
	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public Date getFehcaEntrega() {
		return fechaEntrega;
	}

	public void setFehcaEntrega(Date fehcaEntrega) {
		this.fechaEntrega = fehcaEntrega;
	}

	public String getEstadoPedido() {
		return estadoPedido;
	}

	public void setEstadoPedido(String estadoPedido) {
		this.estadoPedido = estadoPedido;
	}




	public Date getFechaEntrega() {
		return fechaEntrega;
	}




	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}




	public List<DetallePedidoEntity> getDetallePedidoEntities() {
		return detallePedidoEntities;
	}




	public void setDetallePedidoEntities(List<DetallePedidoEntity> detallePedidoEntities) {
		this.detallePedidoEntities = detallePedidoEntities;
	}




	public UsuarioEntity getUsuarioEntity() {
		return usuarioEntity;
	}




	public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}
	
	
	
}
