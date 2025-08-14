package com.ejemplo.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ejemplo.productos")

public class AplicacionProductos {

	public static void main(String[] args) {
		SpringApplication.run(AplicacionProductos.class, args);
	}

}
