package com.springboot;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/*").allowedOrigins("http://localhost:4200") // Permitir solicitudes de Angular
				.allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos permitidos
				.allowedHeaders("");
	}
	
	//Acceso a las imagenes
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Configura el handler para servir imágenes desde "uploads/"
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/"); // Cambiar esta ruta si se usa una carpeta externa
    }
}
