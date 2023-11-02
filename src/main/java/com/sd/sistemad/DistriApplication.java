package com.sd.sistemad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DistriApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistriApplication.class, args);
    }

}
