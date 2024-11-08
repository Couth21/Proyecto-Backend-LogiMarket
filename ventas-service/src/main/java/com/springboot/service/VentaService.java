package com.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.dto.VentaDto;
import com.springboot.entity.DetalleVentaEntity;
import com.springboot.entity.VentaEntity;
import com.springboot.repository.VentaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class VentaService 
{
	@Autowired
    private VentaRepository ventaRepository;

	
	public VentaDto registrarVenta(VentaDto ventaDto) {
        if (ventaDto.getDetallesVenta() == null || ventaDto.getDetallesVenta().isEmpty()) {
            throw new IllegalArgumentException("Se deben proporcionar detalles de venta.");
        }

        VentaEntity venta = convertirADto(ventaDto);
        venta = ventaRepository.save(venta);
        return convertirADto(venta);
    }

    public List<VentaDto> listarVentas() {
        return ventaRepository.findAll().stream()
            .map(this::convertirADto)
            .collect(Collectors.toList());
    }

    private VentaEntity convertirADto(VentaDto ventaDto) {
        VentaEntity venta = new VentaEntity();
        venta.setIdVenta(ventaDto.getIdVenta());
        venta.setFechaVenta(ventaDto.getFechaVenta());
        venta.setTotalVenta(ventaDto.getTotalVenta());
        
        // Manejar la conversión de detalles de venta
        List<DetalleVentaEntity> detalles = ventaDto.getDetallesVenta().stream()
            .map(detalleDto -> {
                DetalleVentaEntity detalle = new DetalleVentaEntity();
                detalle.setCantidad(detalleDto.getCantidad());
                detalle.setPrecioVenta(detalleDto.getPrecioVenta());
                detalle.setVentaEntity(venta); // Establecer la relación con VentaEntity
                return detalle;
            }).collect(Collectors.toList());
        
        venta.setDetallesVentaEntity(detalles);
        return venta;
    }

    private VentaDto convertirADto(VentaEntity venta) {
        VentaDto dto = new VentaDto();
        dto.setIdVenta(venta.getIdVenta());
        dto.setFechaVenta(venta.getFechaVenta());
        dto.setTotalVenta(venta.getTotalVenta());
        
        // Manejar la conversión de detalles de venta
        List<VentaDto.DetalleVentaDto> detallesDto = venta.getDetallesVentaEntity().stream()
            .map(detalle -> {
                VentaDto.DetalleVentaDto detalleDto = new VentaDto.DetalleVentaDto();
                detalleDto.setIdDetalleVenta(detalle.getIdDetalleVenta());
                detalleDto.setCantidad(detalle.getCantidad());
                detalleDto.setPrecioVenta(detalle.getPrecioVenta());
                return detalleDto;
            }).collect(Collectors.toList());
        
        dto.setDetallesVenta(detallesDto);
        return dto;
    }
}
