package com.springboot.dto;

import java.sql.Date;

public class PedidoDto 
{
	private Date fechaPedido;
	private Date fechaEntrega;
	private String estadoPedido;
	
	
	
	public PedidoDto(Date fechaPedido, Date fechaEntrega, String estadoPedido) {
		//super();
		this.fechaPedido = fechaPedido;
		this.fechaEntrega = fechaEntrega;
		this.estadoPedido = estadoPedido;
	}
	
	public PedidoDto() {
		// TODO Auto-generated constructor stub
	}

	
	
	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public String getEstadoPedido() {
		return estadoPedido;
	}

	public void setEstadoPedido(String estadoPedido) {
		this.estadoPedido = estadoPedido;
	}
	

}
