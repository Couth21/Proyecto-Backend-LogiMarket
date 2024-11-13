package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import com.springboot.dto.UsuarioDto;
import com.springboot.security.JwtTokenUtil;
import com.springboot.service.UserServiceClient; // Asegúrate de importar la clase correcta

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserServiceClient usuarioServiceClient; // Asegúrate de que esté inyectado aquí

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // Obtiene el UsuarioDto desde UserServiceClient
        UsuarioDto user = usuarioServiceClient.getUsuarioByUsername(request.getUsername());

        // Verifica que el usuario existe y la contraseña es correcta
        if (user != null && user.getContrasena().equals(request.getPassword())) {
            // Genera el token con el usuario y el rol
            String token = jwtTokenUtil.generateToken(user.getUsuario(), user.getRol());
            return ResponseEntity.ok(new JwtResponse(token, user));
        }
        
        // Si las credenciales no son válidas, retorna un error 401
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

/*class JwtResponse {
    private String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    // Getter
    public String getToken() {
        return token;
    }
}*/
//Clase JwtResponse modificada para incluir toda la información del usuario y RolEntity
class JwtResponse {
    private String token;
    private long idUsuario;
    private String usuario;
    private String contrasena;
    private String email;
    private int ruc;
    private int dni;
    private String nombreEmpresa;
    private String direccion;
    private int idRol;
    private String rol;

    public JwtResponse(String token, UsuarioDto user) {
        this.token = token;
        this.idUsuario = user.getIdUsuario();
        this.usuario = user.getUsuario();
        this.contrasena = user.getContrasena();
        this.email = user.getEmail();
        this.ruc = user.getRuc();
        this.dni = user.getDni();
        this.nombreEmpresa = user.getNombreEmpresa();
        this.direccion = user.getDireccion();
        this.idRol = user.getIdRol();  // idRol directo
        this.rol = user.getRol();      // rol directo
    }

    // Getters para cada campo
    public String getToken() {
        return token;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getEmail() {
        return email;
    }

    public int getRuc() {
        return ruc;
    }

    public int getDni() {
        return dni;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getIdRol() {
        return idRol;
    }

    public String getRol() {
        return rol;
    }

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
    
}
