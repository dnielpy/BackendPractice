package com.example.demo;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@org.springframework.stereotype.Controller
public class Controller {



    public Mono<String> getWeater(){
        WebClient.Builder webClientBuilder = WebClient.builder();
        WeatherService weatherService = new WeatherService(webClientBuilder);

        return weatherService.getWeatherData();
    }
}
