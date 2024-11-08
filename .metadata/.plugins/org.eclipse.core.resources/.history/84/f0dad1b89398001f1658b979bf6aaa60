package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PedidosServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PedidosServiceApplication.class, args);
	}

	// ---Esto se esta agregando
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	// ---

}
