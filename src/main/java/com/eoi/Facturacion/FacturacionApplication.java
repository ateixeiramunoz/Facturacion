package com.eoi.Facturacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication
@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})

public class FacturacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(FacturacionApplication.class, args);
	}

}
