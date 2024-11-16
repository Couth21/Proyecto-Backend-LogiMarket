package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class InventarioServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventarioServiceApplication.class, args);

	}
	
	//--- Esto no es necesario en inventario-service
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	//---

}
