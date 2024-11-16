package com.springboot.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.springboot.model.EstadoPedido;

public class PedidoRequest {

    private LocalDate fechaEntrega;
    private EstadoPedido estadoPedido; // Estado del pedido
    private List<ProductoRequest> productos = new ArrayList<>(); // Inicializar con lista vacía
    private List<DetallePedidoRequest> detallesPedido; // Asegúrate de que este campo existe
    private UsuarioRequest usuarioEntity;


    // Getters y setters
    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public List<ProductoRequest> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoRequest> productos) {
        this.productos = productos;
    }
    
    public List<DetallePedidoRequest> getDetallesPedido() { // Este es el método que falta
        return detallesPedido;
    }

    public void setDetallesPedido(List<DetallePedidoRequest> detallesPedido) {
        this.detallesPedido = detallesPedido;
    }

	public UsuarioRequest getUsuarioEntity() {
		return usuarioEntity;
	}

	public void setUsuarioEntity(UsuarioRequest usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}
    
    
}


