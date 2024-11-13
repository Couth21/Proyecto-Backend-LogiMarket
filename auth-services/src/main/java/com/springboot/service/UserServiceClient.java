package com.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.springboot.dto.UsuarioDto;
import com.springboot.model.UsuarioEntity;

@Service
public class UserServiceClient {



    /*
    public User getUserByUsername(String username) {
        String url = "http://localhost:9091/usuario/byUsername/" + username;
        User user = restTemplate.getForObject(url, User.class);

        // Verifica si el usuario tiene la contraseña cargada correctamente
        System.out.println("Usuario obtenido: " + user);
        return user;
    }*/
    
    private final RestTemplate restTemplate = new RestTemplate();

    public UsuarioDto getUsuarioByUsername(String username) {
        String url = "http://localhost:9091/usuario/byUsername/" + username;

        // Deserializa la respuesta en UsuarioEntity
        UsuarioEntity usuarioEntity = restTemplate.getForObject(url, UsuarioEntity.class);

        // Convertir UsuarioEntity a UsuarioDto
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setIdUsuario(usuarioEntity.getIdUsuario());
        usuarioDto.setUsuario(usuarioEntity.getUsuario());
        usuarioDto.setContrasena(usuarioEntity.getContrasena());
        usuarioDto.setEmail(usuarioEntity.getEmail());
        usuarioDto.setRuc(usuarioEntity.getRuc());
        usuarioDto.setDni(usuarioEntity.getDni());
        usuarioDto.setNombreEmpresa(usuarioEntity.getNombreEmpresa());
        usuarioDto.setDireccion(usuarioEntity.getDireccion());

        // Mapea los datos de RolEntity a los campos de UsuarioDto
        if (usuarioEntity.getRolEntity() != null) {
            usuarioDto.setIdRol(usuarioEntity.getRolEntity().getIdRol());
            usuarioDto.setRol(usuarioEntity.getRolEntity().getRol());
        }

        // Retorna el UsuarioDto ya transformado
        return usuarioDto;
    }
}
