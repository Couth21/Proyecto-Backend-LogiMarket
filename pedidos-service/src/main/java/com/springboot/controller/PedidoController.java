package com.springboot.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.dto.PedidoRequest;
import com.springboot.entity.PedidoEntity;
import com.springboot.repository.PedidoRepository;
import com.springboot.service.PedidoService;


@RestController
@RequestMapping("/pedido")
@CrossOrigin("*")
public class PedidoController {
	@Autowired
	private PedidoService pedidoService;
	
	
	@Autowired
	private PedidoRepository pedidoRepository;
	


    @GetMapping("/listarPedido/{id}")
    public PedidoEntity obtenerPedidoPorId(@PathVariable int id) {
        return pedidoService.obtenerPedidoPorId(id);
    }
    
    

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }
    
 
    @PostMapping("/crearPedido")
    public ResponseEntity<PedidoEntity> crearPedido(@RequestBody PedidoRequest pedidoRequest) {
        PedidoEntity pedido = pedidoService.procesarPedido(pedidoRequest);
        return ResponseEntity.ok(pedido); // Devuelve el pedido completo, incluyendo el código generado
    }
    
    
    @GetMapping("/listarPedido")
    public ResponseEntity<List<PedidoEntity>> listarPedidos() {
        List<PedidoEntity> pedidos = pedidoRepository.findAll();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }
    
    
    @DeleteMapping("eliminarPedido/{id}")
    public ResponseEntity<?> eliminarPedido(@PathVariable int id) {
        pedidoService.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }

    
    @PutMapping("/editar/{idPedido}")
    public ResponseEntity<PedidoEntity> editarEstadoYFechaEntrega(
            @PathVariable int idPedido,
            @RequestBody Map<String, Object> actualizacion) {
        try {
            LocalDate nuevaFechaEntrega = LocalDate.parse(actualizacion.get("fechaEntrega").toString());
            String nuevoEstadoPedido = actualizacion.get("estadoPedido").toString();

            PedidoEntity pedidoActualizado = pedidoService.editarEstadoYFechaEntrega(idPedido, nuevaFechaEntrega, nuevoEstadoPedido);
            return ResponseEntity.ok(pedidoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null); // Manejo de errores
        }
    }
    
    
    @PatchMapping("/cancelar/{idPedido}")
    public ResponseEntity<String> cancelarPedido(@PathVariable int idPedido) {
        try {
            pedidoService.cancelarPedido(idPedido);
            return ResponseEntity.ok("Pedido cancelado correctamente. Stock restaurado y pedido eliminado.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    
}
