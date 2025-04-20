package org.pokeService;

import org.pokeService.service.PokeApiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class PokeApiController {

    private final PokeApiService pokeApiService;

    public PokeApiController(PokeApiService pokeApiService) {
        this.pokeApiService = pokeApiService;
    }

    public static void main(String[] args) {
        SpringApplication.run(PokeApiController.class, args);
    }

    // Endpoint para buscar informações de um Pokémon
    //curl http://localhost:8080/api/pokemon/bulbasaur | jq
    //curl http://localhost:8080/api/pokemon/1 | jq
    @GetMapping("/pokemon/{name}")
    public Mono<ResponseEntity<Object>> obterPokemon(@PathVariable String name) {
        return pokeApiService.getPokemon(name)
                .map(pokemon -> ResponseEntity.ok(pokemon))
                .onErrorResume(ex -> Mono.just(
                        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Erro ao buscar dados do Pokémon: " + ex.getMessage())
                ));
    }

    // Endpoint para buscar informações de uma Location
    // curl http://localhost:8080/api/location/canalave-city-area | jq
    // curl http://localhost:8080/api/location/1 | jq
    @GetMapping("/location/{name}")
    public Mono<ResponseEntity<Object>> obterLocation(@PathVariable String name) {
        return pokeApiService.getLocation(name)
                .map(location -> ResponseEntity.ok(location))
                .onErrorResume(ex -> Mono.just(
                        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Erro ao buscar dados da Location: " + ex.getMessage())
                ));
    }
}
