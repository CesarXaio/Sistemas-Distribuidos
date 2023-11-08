package com.sd.sistemasd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SistemasdApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemasdApplication.class, args);
	}

}
