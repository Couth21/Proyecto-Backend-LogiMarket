package com.springboot.exception; // Asegúrate de ponerla en el paquete correcto

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
