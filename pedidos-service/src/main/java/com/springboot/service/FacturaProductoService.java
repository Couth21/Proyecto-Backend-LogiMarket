package com.springboot.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springboot.dto.FacturaProducto;
import com.springboot.entity.PedidoEntity;
import com.springboot.repository.DetallePedidoRepository;
import com.springboot.repository.PedidoRepository;

import jakarta.transaction.Transactional;




@Service
@Transactional
public class FacturaProductoService {

    private final PedidoRepository pedidoRepository;

    public FacturaProductoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<FacturaProducto> listarFacturas() {
        List<PedidoEntity> pedidos = pedidoRepository.findAll();

        return pedidos.stream()
            .map(pedido -> new FacturaProducto(
                pedido.getIdPedido(),  // ya es int, no llamar intValue()
                pedido.getFechaPedido(),
                pedido.getFechaEntrega(),
                pedido.getCodigo(),
                pedido.getUsuarioEntity(),
                pedido.getTotal()
            ))
            .collect(Collectors.toList());
    }

}