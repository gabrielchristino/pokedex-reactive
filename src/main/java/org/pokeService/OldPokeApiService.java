package org.pokeService;

import reactor.core.publisher.Mono;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class OldPokeApiService  implements CommandLineRunner {
    private final WebClient webClient;

    // Injeção do WebClient.Builder pelo Spring Boot
    public OldPokeApiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://pokeapi.co/api/v2")
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(OldPokeApiService.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        getPokemon("bulbasaur")
                .subscribe(
                        response -> System.out.println("Resposta da PokeAPI: " + response.getName()),
                        error -> System.err.println("Erro: " + error.getMessage())
                );

        getLocation("canalave-city")
                .subscribe(
                        response -> System.out.println("Resposta da PokeAPI: " + response.getName()),
                        error -> System.err.println("Erro: " + error.getMessage())
                );

    }

    public Mono<Pokemon> getPokemon(String pokemonName) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/pokemon/{nome}").build(pokemonName))
                .retrieve()
                .bodyToMono(Pokemon.class);
    }

    public Mono<Location> getLocation(String cityName) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/location/{cityName}").build(cityName))
                .retrieve()
                .bodyToMono(Location.class);
    }
}
