package com.springboot.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.springboot.entity.UsuarioEntity;

public class FacturaProducto {
    private Integer idPedido;  // cambiado a Integer
    private LocalDate fechaPedido;
    private LocalDate fechaEntrega;
    private String codigo;
    private UsuarioEntity usuarioEntity;
    private BigDecimal total;

    public FacturaProducto() {}

    public FacturaProducto(Integer idPedido, LocalDate fechaPedido, LocalDate fechaEntrega, String codigo,
                           UsuarioEntity usuarioEntity, BigDecimal total) {
        this.idPedido = idPedido;
        this.fechaPedido = fechaPedido;
        this.fechaEntrega = fechaEntrega;
        this.codigo = codigo;
        this.usuarioEntity = usuarioEntity;
        this.total = total;
    }

    // Getters y setters
    public Integer getIdPedido() { return idPedido; }
    public void setIdPedido(Integer idPedido) { this.idPedido = idPedido; }

    public LocalDate getFechaPedido() { return fechaPedido; }
    public void setFechaPedido(LocalDate fechaPedido) { this.fechaPedido = fechaPedido; }

    public LocalDate getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(LocalDate fechaEntrega) { this.fechaEntrega = fechaEntrega; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public UsuarioEntity getUsuarioEntity() { return usuarioEntity; }
    public void setUsuarioEntity(UsuarioEntity usuarioEntity) { this.usuarioEntity = usuarioEntity; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    
    //verificacion
}
