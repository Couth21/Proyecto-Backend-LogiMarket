package com.springboot.dto;

import java.sql.Date;
import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;

public class VentaDto 
{
	private Long idVenta;
    private Date fechaVenta; // Cambiar a LocalDate si es necesario
    private List<DetalleVentaDto> detallesVenta;
    private Double totalVenta;

    // Getters y Setters
    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public List<DetalleVentaDto> getDetallesVenta() {
        return detallesVenta;
    }

    public void setDetallesVenta(List<DetalleVentaDto> detallesVenta) {
        this.detallesVenta = detallesVenta;
    }

    public Double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(Double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public static class DetalleVentaDto {
        private Long idDetalleVenta;
        private Integer cantidad;
        private Double precioVenta;

        // Getters y Setters
        public Long getIdDetalleVenta() {
            return idDetalleVenta;
        }

        public void setIdDetalleVenta(Long idDetalleVenta) {
            this.idDetalleVenta = idDetalleVenta;
        }

        public Integer getCantidad() {
            return cantidad;
        }

        public void setCantidad(Integer cantidad) {
            this.cantidad = cantidad;
        }

        public Double getPrecioVenta() {
            return precioVenta;
        }

        public void setPrecioVenta(Double precioVenta) {
            this.precioVenta = precioVenta;
        }
    }
}
