package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.springboot.client") // Asegúrate de escanear el paquete correcto
@ComponentScan(basePackages = "com.springboot") // Escanea todos los paquetes
public class PedidosServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PedidosServiceApplication.class, args);
    }
}