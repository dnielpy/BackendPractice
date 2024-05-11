package com.example.demo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/json")
    public String json() {
        return "Aqui se devuelve el archivo JSON";
    }
    @GetMapping("/xml")
    public String xml() {
        return "Aqui se devuelve el archivo XML";
    }
    @GetMapping("/csv")
    public String csv() {
        return "Aqui se devuelve el archivo CSV";
    }
}