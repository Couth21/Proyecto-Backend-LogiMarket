package com.springboot.controller;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.dto.InventarioDto;
import com.springboot.entity.CategoriaEntity;
import com.springboot.entity.InventarioEntity;
import com.springboot.entity.ProductoEntity;
import com.springboot.entity.SubcategoriaEntity;
import com.springboot.entity.UsuarioEntity;
import com.springboot.service.InventarioService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/inventario")
@CrossOrigin("*")
public class InventarioController 
{
	@Autowired
	InventarioService inventarioService;
	
	
	
    
    
    @GetMapping("/listarCategorias")
    public ResponseEntity<List<CategoriaEntity>> listarCategorias() {
        List<CategoriaEntity> categorias = inventarioService.listarCategorias();
        return ResponseEntity.ok(categorias);
    }
    
    //--------------------------------------------------------------------
    @GetMapping("/listarSubcategorias/{idCategoria}")
    public ResponseEntity<?> listarSubcategoriasPorCategoria(@PathVariable("idCategoria") int idCategoria) {
        try {
            List<SubcategoriaEntity> subcategorias = inventarioService.obtenerSubcategoriasPorCategoria(idCategoria);
            return ResponseEntity.ok(subcategorias);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping("/listarSubcategorias")
    public ResponseEntity<List<SubcategoriaEntity>> listarSubcategorias() {
        List<SubcategoriaEntity> subcategorias = inventarioService.listarSubcategorias();
        return ResponseEntity.ok(subcategorias);
    }
    //-----------------------------------------------------------------------
    
    @GetMapping("/listarProductos")
    public ResponseEntity<List<ProductoEntity>> listarProductos() {
        List<ProductoEntity> productos = inventarioService.listarProductos();
        return ResponseEntity.ok(productos);
    }
    
    
    @GetMapping("/listarProductos/{idSubCategoria}")
    public ResponseEntity<?> listarProductosPorSubcategoria(@PathVariable int idSubCategoria) {
        try {
            List<ProductoEntity> productos = inventarioService.obtenerProductosPorSubcategoria(idSubCategoria);
            return ResponseEntity.ok(productos);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    
    
    /*
    @GetMapping("/listarInventarios")
    public ResponseEntity<List<InventarioEntity>> listarInventarios() {
        List<InventarioEntity> inventarios = inventarioService.listarInventarios();
        return ResponseEntity.ok(inventarios);
    }
    
    */
 
    @GetMapping("/listarInventarios")
    public ResponseEntity<List<InventarioEntity>> listarInventarios() {
        List<InventarioEntity> inventarios = inventarioService.listarInventarios();
        // Para cada inventario, cargar el usuario a través del servicio externo
        inventarios.forEach(inventario -> {
            if (inventario.getUsuarioEntity() == null) {
                UsuarioEntity usuario = inventarioService.getUsuarioEntity(inventario.getUsuarioEntity().getIdUsuario());
                inventario.setUsuarioEntity(usuario);
            }
        });
        return ResponseEntity.ok(inventarios);
    }
  
    
    
    
	
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        if (!inventarioService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inventario no encontrado");
        }
        inventarioService.delete(id);
        return ResponseEntity.ok("Inventario eliminado exitosamente");
    }
    
	@GetMapping("/detail/{id}")
	public ResponseEntity<InventarioEntity> getInventarioById(@PathVariable int id)
	{
		Optional<InventarioEntity> inventarioEntity = inventarioService.getOne(id);
		return inventarioEntity.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	/*
	 // Endpoint para obtener los inventarios de un usuario específico
	@GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<InventarioEntity>> obtenerInventarioPorUsuario(@PathVariable long idUsuario) {
        try {
            List<InventarioEntity> inventarios = inventarioService.obtenerInventarioPorUsuario(idUsuario);
            return ResponseEntity.ok(inventarios);
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }
	*/
	
	
	// Endpoint para editar categoría
	@PutMapping("/editarCategoria")
	public ResponseEntity<?> editarCategoria(@Valid @RequestBody CategoriaEntity categoria) {
	    try {
	        CategoriaEntity categoriaActualizada = inventarioService.editarCategoria(categoria);
	        return ResponseEntity.ok(categoriaActualizada);
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la categoría.");
	    }
	}

    // Endpoint para editar subcategoría
    @PutMapping("/editarSubcategoria")
    public ResponseEntity<?> editarSubcategoria(@Valid @RequestBody SubcategoriaEntity subcategoria) {
        try {
            SubcategoriaEntity subcategoriaActualizada = inventarioService.editarSubcategoria(subcategoria);
            return ResponseEntity.ok(subcategoriaActualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la subcategoría.");
        }
    }

    // Endpoint para editar producto
    @PutMapping("/editarProducto")
    public ResponseEntity<?> editarProducto(@Valid @RequestBody ProductoEntity producto) {
        try {
            ProductoEntity productoActualizado = inventarioService.editarProducto(producto);
            return ResponseEntity.ok(productoActualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el producto.");
        }
    }

    // Endpoint para editar inventario
    @PutMapping("/editarInventario")
    public ResponseEntity<?> editarInventario(@Valid @RequestBody InventarioEntity inventario) {
        try {
            InventarioEntity inventarioActualizado = inventarioService.editarInventario(inventario);
            return ResponseEntity.ok(inventarioActualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el inventario.");
        }
    }
	
	
	
	
	
	
	
	@PostMapping("/crearCategoria")
    public ResponseEntity<String> crearCategoria(@Valid @RequestBody CategoriaEntity categoriaEntity) {
        inventarioService.crearCategoria(categoriaEntity);
        return ResponseEntity.ok("Categoría creada exitosamente");
    }
//-----------------------------------------------
    @PostMapping("/crearSubcategoria")
    public ResponseEntity<String> crearSubcategoria(@Valid @RequestBody SubcategoriaEntity subcategoriaEntity) {
        inventarioService.crearSubcategoria(subcategoriaEntity);
        return ResponseEntity.ok("Subcategoría creada exitosamente");
    }

    @PostMapping("/subcategoria/{idCategoria}")
    public ResponseEntity<String> crearSubcategoriaEnCategoria(@PathVariable int idCategoria, @Valid @RequestBody SubcategoriaEntity subcategoriaEntity) {
        try {
            inventarioService.crearSubcategoriaEnCategoria(subcategoriaEntity, idCategoria);
            return ResponseEntity.ok("Subcategoría creada y asociada a la categoría exitosamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
//------------------------------------------------------
    
    
    
    
    
    
    @PostMapping("/crearProducto")
    public ResponseEntity<String> crearProducto(@Valid @RequestBody ProductoEntity productoEntity) {
        inventarioService.crearProducto(productoEntity);
        return ResponseEntity.ok("Producto creado exitosamente");
    }
    

    @PostMapping("/crearInventario")
    public ResponseEntity<String> crearInventario(@Valid @RequestBody InventarioEntity inventarioEntity) {
        inventarioService.crearInventario(inventarioEntity);
        return ResponseEntity.ok("Inventario creado exitosamente");
    }
	

    
  /*
 // Endpoint para obtener los productos de un proveedor específico
    @GetMapping("/productos/usuario/{idUsuario}")
    public ResponseEntity<?> obtenerProductosPorUsuario(@PathVariable long idUsuario) {
        try {
            List<ProductoEntity> productos = inventarioService.obtenerProductosPorUsuario(idUsuario);
            return ResponseEntity.ok(productos);
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acceso denegado. Solo los proveedores pueden ver los productos.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener los productos del usuario.");
        }
    }
    */
    
}