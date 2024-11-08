package com.springboot.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.springboot.entity.UsuarioEntity;
import com.springboot.repository.UsuarioRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class UsuarioService 
{
	@Autowired
	UsuarioRepository usuarioRepository;
    
    public boolean esBodeguero(long idUsuario) {
        Optional<UsuarioEntity> usuario = usuarioRepository.findById((int) idUsuario); // Cast to Integer
        return usuario.isPresent() && usuario.get().getRolEntity().getIdRol() == 2;
    }

    //------------------------------------------------
	
	public List<UsuarioEntity> list()
	{
		return usuarioRepository.findAll();
	}
	
	public Optional<UsuarioEntity> getOne(int id)
	{
		return usuarioRepository.findById(id);
	}
	
	public Optional<UsuarioEntity> getByEmail(String email)
	{
		return usuarioRepository.findByEmail(email);
	}
	
	public void save(UsuarioEntity usuarioEntity)
	{
		usuarioRepository.save(usuarioEntity);
	}
	
	public void delete(int id)
	{
		usuarioRepository.deleteById(id);
	}
	
	public boolean existsById(int id)
	{
		return usuarioRepository.existsById(id);
	}
	
	public boolean existsByEmail(String email)
	{
		return usuarioRepository.existsByEmail(email);
	}
	
	public List<UsuarioEntity> findByRolId(Integer rolId) {
	    return usuarioRepository.findByRolId(rolId);
	}
	
	public void saveUsuario(UsuarioEntity usuarioEntity) {
        usuarioRepository.save(usuarioEntity);
    }


	public String saveUsuario1(UsuarioEntity usuarioEntity) {

	    // Check for required fields with more specific messages
	    if (usuarioEntity.getUsuario() == null || usuarioEntity.getUsuario().isEmpty()) {
	        return "Error: El nombre de usuario es obligatorio.";
	    }
	    if (usuarioEntity.getContraseña() == null || usuarioEntity.getContraseña().isEmpty()) {
	        return "Error: La contraseña es obligatoria.";
	    }
	    if (usuarioEntity.getEmail() == null || usuarioEntity.getEmail().isEmpty()) {
	        return "Error: El email es obligatorio.";
	    }
	    if (usuarioEntity.getRuc() <= 0) {
	        return "Error: El RUC es obligatorio.";
	    }
	    if (usuarioEntity.getDni() <= 0) {
	        return "Error: El DNI es obligatorio.";
	    }
	    if (usuarioEntity.getNombreEmpresa() == null || usuarioEntity.getNombreEmpresa().isEmpty()) {
	        return "Error: El nombre de la empresa es obligatorio.";
	    }
	    if (usuarioEntity.getDireccion() == null || usuarioEntity.getDireccion().isEmpty()) {
	        return "Error: La dirección es obligatoria.";
	    }
	    if (usuarioEntity.getRolEntity() == null || usuarioEntity.getRolEntity().getIdRol() <= 0) {
	        // Specific message for missing or invalid idRol
	        return "Error: El rol es obligatorio. Debe seleccionar un rol válido.";
	    }
	    

	    // Check for duplicate email
	    if (usuarioRepository.existsByEmail(usuarioEntity.getEmail())) {
	        return "Error: El Email ya existe.";
	    }

	    // Check for duplicate DNI (if applicable)
	    if (usuarioEntity.getDni() > 0 && usuarioRepository.existsByDni(usuarioEntity.getDni())) {
	        return "Error: El DNI ya existe.";
	    }

	    // Check for duplicate username
	    if (usuarioRepository.existsByUsuario(usuarioEntity.getUsuario())) {
	        return "Error: El Usuario ya existe.";
	    }

	    // Save the user
	    usuarioRepository.save(usuarioEntity);
	    return "Usuario creado exitosamente!";
	}
}
