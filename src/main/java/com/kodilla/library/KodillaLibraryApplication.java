package com.kodilla.library;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class KodillaLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(KodillaLibraryApplication.class, args);
	}

}
