package com.springboot.service;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.springboot.dto.InventarioDto;
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
public class InventarioService 
{
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
	
	
	/*
	
	public UsuarioEntity getProductoEntity(int idUsuario) {
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
    */
	
	
    
    
    private String getRolDeUsuario(long idUsuario) {
        String url = "http://localhost:9091/usuario/rol/" + idUsuario; // Supón que tienes un endpoint que devuelve el rol
        try {
            return restTemplate.getForObject(url, String.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return "Rol no asignado"; // Rol no encontrado
            }
            throw e; }// Lanza la excepción para otros errores
        }
	
	
	
        public InventarioDto convertirAInventarioDto(InventarioEntity inventario) {
            InventarioDto dto = new InventarioDto();
            dto.setNombreProducto(inventario.getProductoEntity().getNombreProducto());
            dto.setCantidadDisponible(inventario.getCantidadDisponible());
            dto.setPrecioPersonalizado(inventario.getPrecioPersonalizado());
            dto.setEstadoProducto(inventario.getEstadoProducto());
            dto.setFechaVencimiento(inventario.getFechaVencimiento());

            // Obtener el nombre de usuario y rol
            UsuarioEntity usuario = getUsuarioEntity(inventario.getUsuarioEntity().getIdUsuario());
            if (usuario != null) {
                dto.setNombreUsuario(usuario.getUsuario());
                dto.setNombreRol(getRolDeUsuario(usuario.getIdUsuario())); // Llama al método para obtener el rol
            } else {
                dto.setNombreUsuario("Usuario no encontrado");
                dto.setNombreRol("N/A");
            }

            return dto;
        }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public List<ProductoEntity> obtenerProductosPorUsuario(long idUsuario) throws AccessDeniedException {
	    // URL del servicio usuario-roles-service para verificar si el usuario es "proveedor"
	    String url = "http://localhost:9091/usuario/esProveedor/" + idUsuario;
	    ResponseEntity<Boolean> response = restTemplate.getForEntity(url, Boolean.class);
	    Boolean esProveedor = response.getBody();

	    if (!Boolean.TRUE.equals(esProveedor)) {
	        throw new AccessDeniedException("Acceso denegado. Solo los proveedores pueden ver y gestionar los productos.");
	    }

	    return productoRepository.findByUsuarioEntityIdUsuario(idUsuario);
	}
    
    
    
    
    
    
    
    
    
 // Métodos para crear entidades
    public void crearCategoria(CategoriaEntity categoria) {
        categoriaRepository.save(categoria);
    }

    public void crearSubcategoria(SubcategoriaEntity subcategoria) {
        subcategoriaRepository.save(subcategoria);
    }

    public void crearProducto(ProductoEntity producto) {
        productoRepository.save(producto);
    }

    public void crearInventario(InventarioEntity inventario) {
        inventarioRepository.save(inventario);
    }
    
    
    
    //METODO PARA CREAR SUBCATEGORIA
    public void crearSubcategoriaEnCategoria(SubcategoriaEntity subcategoria, int idCategoria) {
        CategoriaEntity categoria = categoriaRepository.findById(idCategoria)
            .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));
        subcategoria.setCategoriaEntity(categoria);
        subcategoriaRepository.save(subcategoria);
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
    
    public List<CategoriaEntity> listarCategorias() {
        return categoriaRepository.findAll();
    }
    
    
    public List<SubcategoriaEntity> listarSubcategorias() {
        return subcategoriaRepository.findAll();
    }
    
    
    public List<ProductoEntity> listarProductos() {
        return productoRepository.findAll();
    }
    
    
    public List<InventarioEntity> listarInventarios() {
        return inventarioRepository.findAll();
    }
    
  
    
    
    //METODO PARA LISTAR SUBCATEGORIA POR ID DE CATEGORIA
    
    public List<SubcategoriaEntity> obtenerSubcategoriasPorCategoria(int idCategoria) {
        CategoriaEntity categoria = categoriaRepository.findById(idCategoria)
            .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));
        return categoria.getSubcategoriaEntity();
    }
    
    //Metodo para listar Producto por Id de subcategoria
    
    public List<ProductoEntity> obtenerProductosPorSubcategoria(int idSubCategoria) {
        SubcategoriaEntity subcategoria = subcategoriaRepository.findById(idSubCategoria)
            .orElseThrow(() -> new IllegalArgumentException("Subcategoría no encontrada"));
        return subcategoria.getProductoEntity();
    }
    
    
 // Métodos de edición para cada entidad

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

    // Método para editar subcategoría
    public SubcategoriaEntity editarSubcategoria(SubcategoriaEntity subcategoria) {
        Optional<SubcategoriaEntity> subcategoriaExistente = subcategoriaRepository.findById(subcategoria.getIdSubCategoria());
        
        if (subcategoriaExistente.isPresent()) {
            SubcategoriaEntity subcategoriaActualizada = subcategoriaExistente.get();

            // Actualizar el nombre de la subcategoría si no es nulo
            if (subcategoria.getNombreSubcategoria() != null) {
                subcategoriaActualizada.setNombreSubcategoria(subcategoria.getNombreSubcategoria());
            }

            // Si se proporciona una categoría, actualiza la relación con `CategoriaEntity`
            if (subcategoria.getCategoriaEntity() != null) {
                CategoriaEntity categoria = categoriaRepository.findById(subcategoria.getCategoriaEntity().getIdCategoria())
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


    // Método para editar producto
    public ProductoEntity editarProducto(ProductoEntity producto) {
        Optional<ProductoEntity> productoExistente = productoRepository.findById(producto.getIdProducto());

        if (productoExistente.isPresent()) {
            ProductoEntity productoActualizado = productoExistente.get();

            if (producto.getNombreProducto() != null) {
                productoActualizado.setNombreProducto(producto.getNombreProducto());
            }
            if (producto.getPrecioBase() != 0) {
                productoActualizado.setPrecioBase(producto.getPrecioBase());
            }
            if (producto.getDescripcion() != null) {
                productoActualizado.setDescripcion(producto.getDescripcion());
            }
            if (producto.getSubcategoriaEntity() != null) {
                SubcategoriaEntity subcategoria = subcategoriaRepository.findById(producto.getSubcategoriaEntity().getIdSubCategoria())
                        .orElseThrow(() -> new IllegalArgumentException("Subcategoría no encontrada."));
                productoActualizado.setSubcategoriaEntity(subcategoria);
            }

            try {
                return productoRepository.save(productoActualizado);
            } catch (DataIntegrityViolationException e) {
                throw new IllegalArgumentException("Ya existe un producto con el mismo nombre en esta subcategoría.");
            }
        } else {
            throw new IllegalArgumentException("Producto no encontrado.");
        }
    }

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
                throw new IllegalArgumentException("Error al actualizar el inventario debido a una violación de integridad de datos.");
            }
        } else {
            throw new IllegalArgumentException("Inventario no encontrado.");
        }
    }

}