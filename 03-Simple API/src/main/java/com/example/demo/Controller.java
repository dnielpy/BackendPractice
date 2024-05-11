package com.example.demo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/json")
    public String hola() {
        return "Aqui se devuelve el archivo JSON";
    }
}