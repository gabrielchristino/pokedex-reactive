package org.pokeService;

import org.springframework.boot.SpringApplication;

public class App {
    public static void main(String[] args) {
        // Inicia a aplicação Spring Boot
        System.out.println("Starting PokeApiController...");
        SpringApplication.run(PokeApiController.class, args);
    }
}
