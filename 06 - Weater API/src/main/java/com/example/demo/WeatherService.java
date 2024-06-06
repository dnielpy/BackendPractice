package com.example.demo;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class WeatherService {
    private final WebClient webClient;

    public WeatherService(WebClient webClient) {
        this.webClient = webClient;
    }
    public Mono<String> getWeather() {
        return webClient.get()
                .uri("https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&current=temperature_2m,wind_speed_10m")
                .retrieve()
                .bodyToMono(String.class);
    }
}