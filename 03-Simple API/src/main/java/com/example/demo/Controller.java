package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
public class Controller {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/json")
    public String json() {
        return "Aqui se devuelve el archivo JSON";
    }
    @GetMapping("/xml")
    public List<Data> xml() {
        //sacar los datos de la base de datos
        List<Data> people = personRepository.findAll();

        return people;
    }
    @GetMapping("/csv")
    public String csv() {
        return "Aqui se devuelve el archivo CSV";
    }
}