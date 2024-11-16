package com.springboot.dto;

public class DetallePedidoRequest {
    private int productoId;
    private int cantidad;
    private int idUsuario;

    

    public DetallePedidoRequest(int productoId, int cantidad, int idUsuario) {
		//super();
		this.productoId = productoId;
		this.cantidad = cantidad;
		this.idUsuario = idUsuario;
	}

	public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
    
    
}
