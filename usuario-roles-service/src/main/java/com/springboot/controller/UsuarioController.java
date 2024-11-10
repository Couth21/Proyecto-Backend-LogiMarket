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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.dto.Mensaje;
import com.springboot.entity.UsuarioEntity;
import com.springboot.service.UsuarioService;


@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioController 
{
	@Autowired
	UsuarioService usuarioService;
	
	//Listar todos los usuarios de todos los roles
	@GetMapping("/listaTodosUsuarios")
	public ResponseEntity<List<UsuarioEntity>> list()
	{
		List<UsuarioEntity> list = usuarioService.list();
		return new ResponseEntity(list,HttpStatus.OK);
	}
	
	//Lista 1 usuario por el id y se muestra todo
	@GetMapping("/detail/{id}")
	public ResponseEntity<UsuarioEntity> getUsuarioById(@PathVariable int id)
	{
		Optional<UsuarioEntity> usuarioEntity = usuarioService.getOne(id);
		return usuarioEntity.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	//Traer todos los proveedores o todos los bodegueros segun su ID
	@GetMapping("/listaPorRolId/{rolId}")
	public ResponseEntity<List<UsuarioEntity>> listByRolId(@PathVariable Integer rolId) {
	    List<UsuarioEntity> usuariosFiltrados = usuarioService.findByRolId(rolId);

	    if (usuariosFiltrados.isEmpty()) {
	        return ResponseEntity.notFound().build(); // No se encontraron usuarios
	    } else {
	        return ResponseEntity.ok(usuariosFiltrados);
	    }
	}
	

	@PostMapping("/crear")
	public ResponseEntity<String> crearUsuario(@RequestBody UsuarioEntity usuarioEntity) {
	    try {
	        String resultado = usuarioService.saveUsuario1(usuarioEntity);
	        return ResponseEntity.ok(resultado);
	    } catch (UsuarioException e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}
	
	public class UsuarioException extends RuntimeException {
	    public UsuarioException(String message) {
	        super(message);
	    }
	}
	
	//Elimina al usuario mediante su ID
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete (@PathVariable("id") int id)
	{
		if (!usuarioService.existsById(id))
			return new ResponseEntity(new Mensaje("No existe el usuario "), HttpStatus.NOT_FOUND);
		usuarioService.delete(id);
		return new ResponseEntity(new Mensaje("Usuario eliminado"), HttpStatus.OK);
	}
	
	
	
	
	
	@GetMapping("/byUsername/{username}")
	public ResponseEntity<UsuarioEntity> getUsuarioByUsername(@PathVariable String username) {
	    Optional<UsuarioEntity> usuarioEntity = usuarioService.getByUsername(username);
	    return usuarioEntity.map(ResponseEntity::ok)
	            .orElseGet(() -> ResponseEntity.notFound().build());
	}

	
	
	
	
	
	//------------------------------------------------------------------------
	//crear un usuario donde se ingresa todos los atributos,sean necesario
	//caso contrario salir una alerta donde te diga 
	//necesitas colocar todos los atributos , tambien añadiendo de que
	//el usuario,email,dni no se puede repetir de lo que ya esta creado
	//y que le salga una alerta de que ya existe
	//------------------------------------------------------------------------
	
	//-----------------------------
	//crear eliminar usuario|||||||
	//------------------------------
	
	//dependiendo al rol va a tener diferentes permisos e interfaz
	
	//cuando la persona quiera editar su usuario de todos los 
	//atributos , puede editar todo a excepcion del rol que eligio
	
	//como haria para ingresar al login y que me salga mi contraseña
	//es incorrecta
	//editar solamente la contraseña del usuario cuando se alla
	//olvidado

}
