package com.clip.crudRestSprinter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CrudRestSprinterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudRestSprinterApplication.class, args);

		System.out.println("Server started port: 8085");
	}

}
