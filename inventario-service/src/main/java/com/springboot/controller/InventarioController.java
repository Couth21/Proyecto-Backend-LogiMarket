package com.springboot.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import com.springboot.dto.ProductoDto;
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
public class InventarioController {
	@Autowired
	InventarioService inventarioService;

//------------------------------------------------------------------------------------
//------------------------------------CATEGORIAS--------------------------------------
//------------------------------------------------------------------------------------

	@GetMapping("/listarCategorias")
	public ResponseEntity<List<CategoriaEntity>> listarCategorias() {
		List<CategoriaEntity> categorias = inventarioService.listarCategorias();
		return ResponseEntity.ok(categorias);
	}

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

	@PostMapping("/crearCategoria")
	public ResponseEntity<String> crearCategoria(@Valid @RequestBody CategoriaEntity categoriaEntity) {
		inventarioService.crearCategoria(categoriaEntity);
		return ResponseEntity.ok("Categoría creada exitosamente");
	}

	@DeleteMapping("/eliminarCategoria/{idCategoria}")
	public ResponseEntity<String> eliminarCategoria(@PathVariable int idCategoria) {
		try {
			String resultado = inventarioService.eliminarCategoria(idCategoria);
			return new ResponseEntity<>(resultado, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
//------------------------------------------------------------------------------------------
//--------------------------------------SUBCATEGORIA----------------------------------------
//------------------------------------------------------------------------------------------

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

	@PostMapping("/crearSubcategoria")
	public ResponseEntity<String> crearSubcategoria(@Valid @RequestBody SubcategoriaEntity subcategoriaEntity) {
		inventarioService.crearSubcategoria(subcategoriaEntity);
		return ResponseEntity.ok("Subcategoría creada exitosamente");
	}

	@DeleteMapping("/eliminarSubcategoria/{idSubCategoria}")
	public ResponseEntity<String> eliminarSubcategoria(@PathVariable int idSubCategoria) {
		try {
			String resultado = inventarioService.eliminarSubcategoria(idSubCategoria);
			return new ResponseEntity<>(resultado, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

//-----------------------------------------------------------------------------------
//------------------------------------PRODUCTOS--------------------------------------
//-----------------------------------------------------------------------------------

	// Endpoint para listar todos los productos
	@GetMapping("/lstProductos")
	public ResponseEntity<List<ProductoEntity>> listarProductos() {
		List<ProductoEntity> productos = inventarioService.listarProductos();
		return new ResponseEntity<>(productos, HttpStatus.OK);
	}

	// Método para crear un producto con id de usuario asociado
	@PostMapping("/crearProducto")
	public ResponseEntity<ProductoEntity> crearProducto(@RequestBody ProductoDto productoDto) {
		ProductoEntity nuevoProducto = inventarioService.crearProducto(productoDto.getProducto(),
				productoDto.getIdUsuario(), productoDto.getIdSubCategoria());
		return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
	}

	// Endpoint para obtener productos por usuario
	@GetMapping("/lstProductos/{idUsuario}")
	public ResponseEntity<List<ProductoEntity>> obtenerProductosPorUsuario(@PathVariable long idUsuario) {
		List<ProductoEntity> productos = inventarioService.obtenerProductosPorUsuario(idUsuario);
		return new ResponseEntity<>(productos, HttpStatus.OK);
	}
	
	@GetMapping("/lstProductos/{idUsuario}/{idSubcategoria}")
	public ResponseEntity<List<ProductoEntity>> listarProductosPorUsuarioYSubcategoria(
	        @PathVariable("idUsuario") long idUsuario,
	        @PathVariable("idSubcategoria") int idSubcategoria) {
	    
	    List<ProductoEntity> productos = inventarioService.obtenerProductosPorUsuarioYSubcategoria(idUsuario, idSubcategoria);
	    
	    return new ResponseEntity<>(productos, HttpStatus.OK);
	}

	// Método alternativo para editar producto usando ProductoEntity
	@PutMapping("/editarProductoDto")
	public ResponseEntity<ProductoEntity> editarProducto(@RequestBody ProductoDto productoDto) {
	    // Obtener el ID del producto directamente desde el objeto ProductoEntity dentro de ProductoDto
	    int idProducto = productoDto.getProducto().getIdProducto();
	    ProductoEntity productoActualizado = inventarioService.editarProducto(
	            productoDto.getProducto(), idProducto, productoDto.getIdUsuario(), productoDto.getIdSubCategoria()
	    );
	    return new ResponseEntity<>(productoActualizado, HttpStatus.OK);
	}

	@DeleteMapping("/eliminarProducto/{idProducto}")
	public ResponseEntity<String> eliminarProducto(@PathVariable long idProducto) {
		try {
			String mensaje = inventarioService.eliminarProducto(idProducto);
			return new ResponseEntity<>(mensaje, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
//--------------------------------------------------------------------------------

	/*
	 * @GetMapping("/listarInventarios") public
	 * ResponseEntity<List<InventarioEntity>> listarInventarios() {
	 * List<InventarioEntity> inventarios = inventarioService.listarInventarios();
	 * return ResponseEntity.ok(inventarios); }
	 * 
	 */

	@GetMapping("/listarInventarios")
	public ResponseEntity<List<InventarioEntity>> listarInventarios() {
		List<InventarioEntity> inventarios = inventarioService.listarInventarios();
		// Para cada inventario, cargar el usuario a través del servicio externo
		inventarios.forEach(inventario -> {
			if (inventario.getUsuarioEntity() == null) {
				UsuarioEntity usuario = inventarioService
						.getUsuarioEntity(inventario.getUsuarioEntity().getIdUsuario());
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
	public ResponseEntity<InventarioEntity> getInventarioById(@PathVariable int id) {
		Optional<InventarioEntity> inventarioEntity = inventarioService.getOne(id);
		return inventarioEntity.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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

//-----------------------------------------------

//------------------------------------------------------

	/*
	 * @PostMapping("/crearProducto") public ResponseEntity<String>
	 * crearProducto(@Valid @RequestBody ProductoEntity productoEntity) {
	 * inventarioService.crearProducto(productoEntity); return
	 * ResponseEntity.ok("Producto creado exitosamente"); }
	 */

	@PostMapping("/crearInventario")
	public ResponseEntity<String> crearInventario(@Valid @RequestBody InventarioEntity inventarioEntity) {
		inventarioService.crearInventario(inventarioEntity);
		return ResponseEntity.ok("Inventario creado exitosamente");
	}

	/*
	 * // Endpoint para obtener los productos de un proveedor específico
	 * 
	 * @GetMapping("/productos/usuario/{idUsuario}") public ResponseEntity<?>
	 * obtenerProductosPorUsuario(@PathVariable long idUsuario) { try {
	 * List<ProductoEntity> productos =
	 * inventarioService.obtenerProductosPorUsuario(idUsuario); return
	 * ResponseEntity.ok(productos); } catch (AccessDeniedException e) { return
	 * ResponseEntity.status(HttpStatus.FORBIDDEN).
	 * body("Acceso denegado. Solo los proveedores pueden ver los productos."); }
	 * catch (Exception e) { return
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
	 * body("Error al obtener los productos del usuario."); } }
	 */

}