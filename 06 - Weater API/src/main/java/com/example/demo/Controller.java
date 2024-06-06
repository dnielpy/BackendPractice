package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/getWeather")
    public String getWeather(){
        WebClient webClient = WebClient.create();
        WeatherService weatherService = new WeatherService("37.7749", "-122.4194", webClient);
        Mono<String> weather = weatherService.getWeather();
        return weather.block();
    }
}

