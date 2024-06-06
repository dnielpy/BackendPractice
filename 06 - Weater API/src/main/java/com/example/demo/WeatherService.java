package com.example.demo;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class WeatherService {
    private String latitude;
    private String longitude;
    private final WebClient webClient;

    public WeatherService(String latitude, String longitude, WebClient webClient) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.webClient = webClient;
    }

    public Mono<String> getWeather(){
        String urlTemplate = "http://api.openweathermap.org/data/2.5/weather?lat={latitude}&lon={longitude}";

        return webClient.get()
                .uri(urlTemplate, latitude, longitude)
                .retrieve()
                .bodyToMono(String.class);
    }
}
