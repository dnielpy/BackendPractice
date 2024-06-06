package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WeatherService {

    private final WebClient webClient;

    public WeatherService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.open-meteo.com/v1/forecast").build();
    }

    public Mono<String> getWeatherData() {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("latitude", "52.52")
                        .queryParam("longitude", "13.41")
                        .queryParam("current", "temperature_2m,wind_speed_10m")
                        .queryParam("hourly", "temperature_2m,relative_humidity_2m,wind_speed_10m")
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }
}