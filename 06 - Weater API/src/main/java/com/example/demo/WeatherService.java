package com.example.demo;

public class WeatherService {
    private final String latitude;
    private final String longitude;

    public WeatherService(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getWeather(){
        //Logica para leer el json

        String url = "api.openweathermap.org/data/2.5/weather?lat={" + latitude + "}&lon={" + longitude + "} ";



        return null;
    }
}
