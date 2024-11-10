package com.springboot.model;

public class User {
    private String username;
    private String password;
    private String email;
    private String role; // Puedes ajustar los campos según lo que uses

    // Constructor vacío (necesario para frameworks como Jackson)
    public User() {
    }

    // Constructor con todos los campos (opcional)
    public User(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Otros métodos (si necesitas, como toString, equals, hashCode)
}
