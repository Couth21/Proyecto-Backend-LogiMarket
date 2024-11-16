package com.springboot.dto;



public class DetallePedidoRequest {
    private int idProducto;
    private int cantidad;
    private int idUsuario;

	// Constructor, getters y setters
    public DetallePedidoRequest() {}

    public int getIdProducto() { // Getter para idProducto
        return idProducto;
    }

    public void setIdProducto(int idProducto) { // Setter para idProducto
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdUsuario() {  // Cambiado a 'getIdUsuario()'
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {  // Cambiado a 'setIdUsuario()'
        this.idUsuario = idUsuario;
    }
    
}