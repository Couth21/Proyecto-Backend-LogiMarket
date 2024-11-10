package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springboot.dto.UsuarioDto;
import com.springboot.security.JwtTokenUtil;
import com.springboot.service.UserServiceClient; // Asegúrate de importar la clase correcta

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserServiceClient usuarioServiceClient; // Asegúrate de que esté inyectado aquí

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        UsuarioDto user = usuarioServiceClient.getUsuarioByUsername(request.getUsername());
        if (user != null && user.getContraseña().equals(request.getPassword())) {
            String token = jwtTokenUtil.generateToken(user.getUsuario(), user.getRol());
            return ResponseEntity.ok(new JwtResponse(token));
        }
        return ResponseEntity.status(401).body("Credenciales inválidas");
    }
}



// Clases auxiliares
class LoginRequest {
    private String username;
    private String password;

    // Getters y setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

class JwtResponse {
    private String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    // Getter
    public String getToken() {
        return token;
    }
}
