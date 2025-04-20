package org.pokeService.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PokeApiService {

    private final WebClient webClient;
    private static final String API_BASE_URL = "https://pokeapi.co/api/v2";

    public PokeApiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl(API_BASE_URL)
                .build();;
    }

    // Consome a rota de Pokémon
    public Mono<Object> getPokemon(String pokemonName) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/pokemon/{nome}").build(pokemonName))
                .retrieve()
                .bodyToMono(Object.class);
    }

    // Consome a rota de Location
    public Mono<Object> getLocation(String cityName) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/location/{cityName}").build(cityName))
                .retrieve()
                .bodyToMono(Object.class);
    }
}