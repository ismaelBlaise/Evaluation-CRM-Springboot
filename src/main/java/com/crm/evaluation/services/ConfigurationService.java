package com.crm.evaluation.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class ConfigurationService {
    private final WebClient webClient;

    public ConfigurationService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8000/api").build();
    }

    public Mono<String> insertConfiguration(String token, Double remiseGlobale) {
        return webClient.post()
                .uri("/configuration")
                .header("Authorization", "Bearer " + token)
                .bodyValue(Map.of("remise_globale", remiseGlobale))
                .retrieve()
                .bodyToMono(String.class);
    }
}

