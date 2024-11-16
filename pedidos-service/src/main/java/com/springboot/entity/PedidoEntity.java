package com.springboot.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.springboot.model.EstadoPedido;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="PEDIDO")
public class PedidoEntity 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPedido;
	private LocalDate fechaPedido;  // Cambiar a LocalDate
    private LocalDate fechaEntrega;  // Cambiar a LocalDate
    @Enumerated(EnumType.STRING)  // Para almacenar el valor como un string
    private EstadoPedido estadoPedido;  // Aquí es donde se guarda el estado del pedido
    private String codigo; // Nuevo campo para el código
	
	
	@ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioEntity usuarioEntity;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
    private List<DetallePedidoEntity> detallesPedido = new ArrayList<>();


    
    
    
    
    
   
	

	
   

    public PedidoEntity(LocalDate fechaPedido, LocalDate fechaEntrega, EstadoPedido estadoPedido, String codigo,
			UsuarioEntity usuarioEntity, List<DetallePedidoEntity> detallesPedido) {
		//super();
		this.fechaPedido = fechaPedido;
		this.fechaEntrega = fechaEntrega;
		this.estadoPedido = estadoPedido;
		this.codigo = codigo;
		this.usuarioEntity = usuarioEntity;
		this.detallesPedido = detallesPedido;
	}

	public PedidoEntity() {
        // Default constructor
    }

    // Getters y setters
    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public UsuarioEntity getUsuarioEntity() {
        return usuarioEntity;
    }

    public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
        this.usuarioEntity = usuarioEntity;
    }

    public List<DetallePedidoEntity> getDetallesPedido() {
        return detallesPedido;
    }

    public void setDetallesPedido(List<DetallePedidoEntity> detallesPedido) {
        this.detallesPedido = detallesPedido;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
    
 // Getters y setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    
    @Transient // No se persiste este campo en la base de datos
    public BigDecimal getTotal() {
        return detallesPedido.stream()
                .map(detalle -> BigDecimal.valueOf(detalle.getSubtotal()))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP); // Redondea el total a 2 decimales
    
    
    }
}