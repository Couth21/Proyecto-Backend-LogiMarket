package com.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.springboot.dto.UsuarioDto;
import com.springboot.model.User;

@Service
public class UserServiceClient {



    /*
    public User getUserByUsername(String username) {
        String url = "http://localhost:9091/usuario/byUsername/" + username;
        User user = restTemplate.getForObject(url, User.class);

        // Verifica si el usuario tiene la contraseña cargada correctamente
        System.out.println("Usuario obtenido: " + user);
        return user;
    }
*/
    
    private final RestTemplate restTemplate = new RestTemplate();

    public UsuarioDto getUsuarioByUsername(String username) {
        String url = "http://localhost:9091/usuario/byUsername/" + username; // Cambia la URL si es necesario
        return restTemplate.getForObject(url, UsuarioDto.class);
    }
}
