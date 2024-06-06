package com.example.demo;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class WeatherService {
    private final WebClient webClient;
    private String latitude;
    private String longitude;

    public WeatherService(WebClient webClient, String latitude, String longitude) {
        this.webClient = webClient;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Mono<String> getWeather() {
        return webClient.get()
                .uri("https://api.open-meteo.com/v1/forecast?latitude="+ latitude +"&longitude="+longitude+"&current=temperature_2m,wind_speed_10m")
                .retrieve()
                .bodyToMono(String.class);
    }
}