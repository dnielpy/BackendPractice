package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class Controller {
    @GetMapping("/getWeather")
    public Mono<String> getWeather(@RequestParam String latitude, String longitude) {
        WebClient webClient = WebClient.create();
        WeatherService weatherService = new WeatherService(webClient, latitude, longitude);
        return weatherService.getWeather();
    }

    @GetMapping("/getTemperature")
    public Mono<String> getTemperature(@RequestParam String latitude, String longitude) {
        WebClient webClient = WebClient.create();
        WeatherService weatherService = new WeatherService(webClient, latitude, longitude);
        return weatherService.getTemperature();
    }

    @GetMapping("/getHumidity")
    public Mono<String> getHumidity(@RequestParam String latitude, String longitude) {
        WebClient webClient = WebClient.create();
        WeatherService weatherService = new WeatherService(webClient, latitude, longitude);
        return weatherService.getHumidity();
    }

    @GetMapping("/getRain")
    public Mono<String> getRain(@RequestParam String latitude, String longitude) {
        WebClient webClient = WebClient.create();
        WeatherService weatherService = new WeatherService(webClient, latitude, longitude);
        return weatherService.getRain();
    }
}

