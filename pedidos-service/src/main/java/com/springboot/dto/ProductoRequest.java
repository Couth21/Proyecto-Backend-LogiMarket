package com.springboot.dto;

public class ProductoRequest {

    private Long idProducto;  // ID del producto
    private int cantidad;  // Cantidad solicitada del producto

    // Getters y setters
    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

