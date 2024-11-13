package com.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.springboot.entity.CategoriaEntity;
import com.springboot.entity.InventarioEntity;
import com.springboot.entity.ProductoEntity;
import com.springboot.entity.SubcategoriaEntity;
import com.springboot.entity.UsuarioEntity;
import com.springboot.repository.CategoriaRepository;
import com.springboot.repository.InventarioRepository;
import com.springboot.repository.ProductoRepository;
import com.springboot.repository.SubcategoriaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class InventarioService {
	@Autowired
	InventarioRepository inventarioRepository;

	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	SubcategoriaRepository subcategoriaRepository;

	@Autowired
	ProductoRepository productoRepository;

	@Autowired
	private RestTemplate restTemplate;

	public UsuarioEntity getUsuarioEntity(long idUsuario) {
		String url = "http://localhost:9091/usuario/detail/" + idUsuario;
		try {
			return restTemplate.getForObject(url, UsuarioEntity.class);
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
				return null;
			}
			throw e;
		}
	}

//------------------------------------------------------------------------------------------------------------------------	
//------------------------------------------------CATEGORIA---------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------

	public void crearCategoria(CategoriaEntity categoria) {
		categoriaRepository.save(categoria);
	}

	public List<CategoriaEntity> listarCategorias() {
		return categoriaRepository.findAll();
	}

	// Método para editar categoría
	public CategoriaEntity editarCategoria(CategoriaEntity categoria) {
		Optional<CategoriaEntity> categoriaExistente = categoriaRepository.findById(categoria.getIdCategoria());

		if (categoriaExistente.isPresent()) {
			CategoriaEntity categoriaActualizada = categoriaExistente.get();

			if (categoria.getCategoria() != null) {
				categoriaActualizada.setCategoria(categoria.getCategoria());
			}

			try {
				return categoriaRepository.save(categoriaActualizada);
			} catch (DataIntegrityViolationException e) {
				throw new IllegalArgumentException("Ya existe una categoría con el mismo nombre.");
			}
		} else {
			throw new IllegalArgumentException("Categoría no encontrada.");
		}
	}

	public String eliminarCategoria(int idCategoria) {
		// Verifica si la categoría existe antes de intentar eliminarla
		if (!categoriaRepository.existsById(idCategoria)) {
			throw new IllegalArgumentException("Categoría no encontrada con ID: " + idCategoria);
		}

		// Si existe, procedemos a eliminar
		categoriaRepository.deleteById(idCategoria);
		return "Categoría eliminada con éxito";
	}

//------------------------------------------------------------------------------------------------------------------------	
//------------------------------------------------SUB CATEGORIA-----------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------

	public String eliminarSubcategoria(int idSubCategoria) {
		// Verifica si la subcategoría existe antes de intentar eliminarla
		if (!subcategoriaRepository.existsById(idSubCategoria)) {
			throw new IllegalArgumentException("Subcategoría no encontrada con ID: " + idSubCategoria);
		}

		// Si existe, procedemos a eliminar
		subcategoriaRepository.deleteById(idSubCategoria);
		return "Subcategoría eliminada con éxito";
	}

	// METODO PARA CREAR SUBCATEGORIA
	public void crearSubcategoriaEnCategoria(SubcategoriaEntity subcategoria, int idCategoria) {
		CategoriaEntity categoria = categoriaRepository.findById(idCategoria)
				.orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));
		subcategoria.setCategoriaEntity(categoria);
		subcategoriaRepository.save(subcategoria);
	}

	public void crearSubcategoria(SubcategoriaEntity subcategoria) {
		subcategoriaRepository.save(subcategoria);
	}

	// Listar subcategoria
	public List<SubcategoriaEntity> listarSubcategorias() {
		return subcategoriaRepository.findAll();
	}

	// METODO PARA LISTAR SUBCATEGORIA POR ID DE CATEGORIA
	public List<SubcategoriaEntity> obtenerSubcategoriasPorCategoria(int idCategoria) {
		CategoriaEntity categoria = categoriaRepository.findById(idCategoria)
				.orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));
		return categoria.getSubcategoriaEntity();
	}

	// Método para editar subcategoría
	public SubcategoriaEntity editarSubcategoria(SubcategoriaEntity subcategoria) {
		Optional<SubcategoriaEntity> subcategoriaExistente = subcategoriaRepository
				.findById(subcategoria.getIdSubCategoria());

		if (subcategoriaExistente.isPresent()) {
			SubcategoriaEntity subcategoriaActualizada = subcategoriaExistente.get();

			// Actualizar el nombre de la subcategoría si no es nulo
			if (subcategoria.getNombreSubcategoria() != null) {
				subcategoriaActualizada.setNombreSubcategoria(subcategoria.getNombreSubcategoria());
			}

			// Si se proporciona una categoría, actualiza la relación con `CategoriaEntity`
			if (subcategoria.getCategoriaEntity() != null) {
				CategoriaEntity categoria = categoriaRepository
						.findById(subcategoria.getCategoriaEntity().getIdCategoria())
						.orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada."));
				subcategoriaActualizada.setCategoriaEntity(categoria);
			}

			try {
				return subcategoriaRepository.save(subcategoriaActualizada);
			} catch (DataIntegrityViolationException e) {
				throw new IllegalArgumentException("Ya existe una subcategoría con el mismo nombre en esta categoría.");
			}
		} else {
			throw new IllegalArgumentException("Subcategoría no encontrada.");
		}
	}

//------------------------------------------------------------------------------------------------------------------------	
//-----------------------------------------------------PRODUCTO-----------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------

	// Asegúrate de que el método editarProducto en InventarioService tenga esta
	// firma
	public ProductoEntity editarProducto(ProductoEntity producto, long idProducto, long idUsuario, int idSubCategoria) {
		// Verificar si el producto existe
		ProductoEntity productoExistente = productoRepository.findById((int) idProducto)
				.orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + idProducto));

		// Obtener el usuario
		UsuarioEntity usuario = getUsuarioEntity(idUsuario);
		if (usuario == null) {
			throw new IllegalArgumentException("Usuario no encontrado con ID: " + idUsuario);
		}

		// Obtener la subcategoría
		SubcategoriaEntity subcategoria = subcategoriaRepository.findById(idSubCategoria).orElseThrow(
				() -> new IllegalArgumentException("Subcategoría no encontrada con ID: " + idSubCategoria));

		// Actualizar los campos del producto
		productoExistente.setNombreProducto(producto.getNombreProducto() != null ? producto.getNombreProducto()
				: productoExistente.getNombreProducto());
		productoExistente.setPrecioBase(
				producto.getPrecioBase() != 0 ? producto.getPrecioBase() : productoExistente.getPrecioBase());
		productoExistente.setDescripcion(
				producto.getDescripcion() != null ? producto.getDescripcion() : productoExistente.getDescripcion());
		productoExistente.setUsuarioEntity(usuario);
		productoExistente.setSubcategoria(subcategoria); // Solo se asigna la subcategoría

		return productoRepository.save(productoExistente);
	}

	// Método para listar todos los productos
	public List<ProductoEntity> listarProductos() {
		return productoRepository.findAll();
	}

	// Método para crear un producto
	public ProductoEntity crearProducto(ProductoEntity producto, long idUsuario, int idSubCategoria) {
		UsuarioEntity usuario = getUsuarioEntity(idUsuario);
		if (usuario == null) {
			throw new IllegalArgumentException("Usuario no encontrado con ID: " + idUsuario);
		}

		SubcategoriaEntity subcategoria = subcategoriaRepository.findById(idSubCategoria).orElseThrow(
				() -> new IllegalArgumentException("Subcategoría no encontrada con ID: " + idSubCategoria));

		producto.setUsuarioEntity(usuario);
		producto.setSubcategoria(subcategoria);

		return productoRepository.save(producto);
	}

	// Eliminar Producto
	public String eliminarProducto(long idProducto) {
		Integer id = (int) idProducto; // Convertir long a Integer
		if (productoRepository.existsById(id)) {
			productoRepository.deleteById(id);
			return "Producto eliminado con éxito con ID: " + idProducto;
		} else {
			throw new IllegalArgumentException("Producto no encontrado con ID: " + idProducto);
		}
	}

	// Método para obtener productos por usuario (usando el campo idUsuario)
	public List<ProductoEntity> obtenerProductosPorUsuario(long idUsuario) {
		return productoRepository.findByUsuarioEntityIdUsuario(idUsuario);
	}

	// Metodo para listar Producto por Id de subcategoria
	public List<ProductoEntity> obtenerProductosPorSubcategoria(int idSubCategoria) {
		SubcategoriaEntity subcategoria = subcategoriaRepository.findById(idSubCategoria)
				.orElseThrow(() -> new IllegalArgumentException("Subcategoría no encontrada"));
		return subcategoria.getProductos();
	}

//-------------------------------------------------------------------------------------------------------------------	
//--------------------------------------------INVENTARIO-------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------

	public void crearInventario(InventarioEntity inventario) {
		inventarioRepository.save(inventario);
	}

	// Métodos CRUD de Inventario
	public Optional<InventarioEntity> getOne(int id) {
		return inventarioRepository.findById(id);
	}

	public List<InventarioEntity> list() {
		return inventarioRepository.findAll();
	}

	public void delete(int id) {
		inventarioRepository.deleteById(id);
	}

	public boolean existsById(int id) {
		return inventarioRepository.existsById(id);
	}

	public boolean existsByCantidadDisponible(int cantidadDisponible) {
		return inventarioRepository.existsByCantidadDisponible(cantidadDisponible);
	}

	public List<InventarioEntity> listarInventarios() {
		return inventarioRepository.findAll();
	}

	// Métodos de edición para cada entidad

	// Método para editar inventario
	public InventarioEntity editarInventario(InventarioEntity inventario) {
		Optional<InventarioEntity> inventarioExistente = inventarioRepository.findById(inventario.getIdInventario());

		if (inventarioExistente.isPresent()) {
			InventarioEntity inventarioActualizado = inventarioExistente.get();

			if (inventario.getCantidadDisponible() != 0) {
				inventarioActualizado.setCantidadDisponible(inventario.getCantidadDisponible());
			}
			if (inventario.getPrecioPersonalizado() != 0) {
				inventarioActualizado.setPrecioPersonalizado(inventario.getPrecioPersonalizado());
			}
			if (inventario.getFechaVencimiento() != null) {
				inventarioActualizado.setFechaVencimiento(inventario.getFechaVencimiento());
			}
			if (inventario.getEstadoProducto() != null) {
				inventarioActualizado.setEstadoProducto(inventario.getEstadoProducto());
			}
			if (inventario.getProductoEntity() != null) {
				ProductoEntity producto = productoRepository.findById(inventario.getProductoEntity().getIdProducto())
						.orElseThrow(() -> new IllegalArgumentException("Producto no encontrado."));
				inventarioActualizado.setProductoEntity(producto);
			}

			try {
				return inventarioRepository.save(inventarioActualizado);
			} catch (DataIntegrityViolationException e) {
				throw new IllegalArgumentException(
						"Error al actualizar el inventario debido a una violación de integridad de datos.");
			}
		} else {
			throw new IllegalArgumentException("Inventario no encontrado.");
		}
	}

}