package com.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.springboot.dto.UsuarioDto; // Asegúrate de que esté correctamente importado
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserServiceClient userServiceClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Llama al método de UserServiceClient para obtener los datos del usuario
        UsuarioDto user = userServiceClient.getUsuarioByUsername(username);
        
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        // Imprime la contraseña obtenida para depuración
        System.out.println("Contraseña obtenida de la base de datos: " + user.getContraseña());

        // Retorna un UserDetails con los datos del usuario
        return new org.springframework.security.core.userdetails.User(
                user.getUsuario(),
                user.getContraseña(),
                Collections.emptyList() // Ajusta los roles y permisos según tu lógica
        );
    }
}
