package com.springboot.model;

public class RolEntity {
	private int idRol;

    private String rol;

    // Constructor vacío
    public RolEntity() {}

    // Getters y Setters
    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
