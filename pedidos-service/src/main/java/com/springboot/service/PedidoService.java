package com.springboot.service;

import com.springboot.client.InventarioClient;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.springboot.dto.DetallePedidoRequest;
import com.springboot.dto.PedidoRequest;
import com.springboot.entity.DetallePedidoEntity;
import com.springboot.entity.PedidoEntity;
import com.springboot.entity.ProductoEntity;
import com.springboot.entity.UsuarioEntity;
import com.springboot.repository.DetallePedidoRepository;
import com.springboot.repository.PedidoRepository;
import com.springboot.repository.ProductoRepository;
import com.springboot.repository.UsuarioRepository;
import com.springboot.exception.ResourceNotFoundException;
import com.springboot.model.EstadoPedido;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoService {

	private static final Logger logger = LoggerFactory.getLogger(PedidoService.class);

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private InventarioClient inventarioClient;

	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private DetallePedidoRepository detallePedidoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	// Método para obtener UsuarioEntity a través de un servicio externo
	public UsuarioEntity obtenerUsuarioPorId(long idUsuario) {
		String url = "http://localhost:9091/usuario/" + idUsuario;
		try {
			return restTemplate.getForObject(url, UsuarioEntity.class);
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
				return null;
			}
			throw e;
		}
	}

	public ProductoEntity obtenerProductoPorId(int idProducto) {
		String url = "http://localhost:9092/inventario/producto/" + idProducto; // URL del microservicio de productos
		try {
			return restTemplate.getForObject(url, ProductoEntity.class);
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
				throw new IllegalArgumentException("Producto no encontrado con ID: " + idProducto);
			}
			throw e;
		}
	}


	@Autowired
	public PedidoService(InventarioClient inventarioClient) {
		this.inventarioClient = inventarioClient;
	}

	// Listar todos los pedidos
	public List<PedidoEntity> listarPedidos() {
		return pedidoRepository.findAll();
	}

	// Obtener pedido por ID
	public PedidoEntity obtenerPedidoPorId(int idPedido) {
		return pedidoRepository.findById(idPedido)
				.orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado con ID: " + idPedido));
	}


	
	
	public PedidoService(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository, ProductoRepository productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
    }
	
	
	public PedidoEntity procesarPedido(PedidoRequest pedidoRequest) {
        PedidoEntity pedido = new PedidoEntity();

        // Generar un código aleatorio único
        String codigoGenerado = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        pedido.setCodigo(codigoGenerado);

        pedido.setFechaPedido(LocalDate.now());
        pedido.setFechaEntrega(pedidoRequest.getFechaEntrega());
        pedido.setEstadoPedido(pedidoRequest.getEstadoPedido());

        UsuarioEntity usuario = usuarioRepository.findById(pedidoRequest.getUsuarioEntity().getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        pedido.setUsuarioEntity(usuario);

        List<DetallePedidoEntity> detalles = new ArrayList<>();
        for (DetallePedidoRequest detalleRequest : pedidoRequest.getDetallesPedido()) {
            ProductoEntity producto = productoRepository.findById(detalleRequest.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            if (producto.getStock() < detalleRequest.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombreProducto());
            }

            producto.setStock(producto.getStock() - detalleRequest.getCantidad());
            productoRepository.save(producto);

            DetallePedidoEntity detalle = new DetallePedidoEntity();
            detalle.setCantidad(detalleRequest.getCantidad());
            detalle.setProducto(producto);
            detalle.setPedido(pedido);
            detalles.add(detalle);
        }

        pedido.setDetallesPedido(detalles);

        // Guardar el pedido con el código generado
        return pedidoRepository.save(pedido);
    }

	
	
	//EDITAR

	public PedidoEntity editarEstadoYFechaEntrega(int idPedido, LocalDate fechaEntrega, String estadoPedido) {
		// Buscar el pedido existente
		PedidoEntity pedidoExistente = pedidoRepository.findById(idPedido)
				.orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

		// Actualizar los campos específicos
		pedidoExistente.setFechaEntrega(fechaEntrega);

		try {
			EstadoPedido nuevoEstado = EstadoPedido.valueOf(estadoPedido.toUpperCase());
			pedidoExistente.setEstadoPedido(nuevoEstado);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("Estado de pedido no válido");
		}

		// Guardar el pedido actualizado
		return pedidoRepository.save(pedidoExistente);
	}
	
	
	//CANCELAR
	@Transactional
	public void cancelarPedido(int idPedido) {
		// Buscar el pedido por su ID
		PedidoEntity pedido = pedidoRepository.findById(idPedido)
				.orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

		// Restaurar el stock de los productos asociados al pedido
		for (DetallePedidoEntity detalle : pedido.getDetallesPedido()) {
			ProductoEntity producto = detalle.getProducto();
			producto.setStock(producto.getStock() + detalle.getCantidad());
			productoRepository.save(producto); // Guardar el nuevo stock
		}

		// Eliminar el pedido
		pedidoRepository.delete(pedido);

		// Verificar si el pedido fue eliminado
		if (pedidoRepository.existsById(idPedido)) {
			throw new RuntimeException("No se pudo eliminar el pedido.");
		}
	}


	// Eliminar un pedido
	public void eliminarPedido(int idPedido) {
		pedidoRepository.deleteById(idPedido);
	}

	// Otros métodos de acceso
	public Optional<PedidoEntity> getOne(int id) {
		return pedidoRepository.findById(id);
	}

	public Optional<PedidoEntity> getByFechaPedido(Date fechaPedido) {
		return pedidoRepository.findByFechaPedido(fechaPedido);
	}

	public void save(PedidoEntity pedidoEntity) {
		pedidoRepository.save(pedidoEntity);
	}

	public List<PedidoEntity> list() {
		return pedidoRepository.findAll();
	}

	public void delete(int id) {
		pedidoRepository.deleteById(id);
	}

}
