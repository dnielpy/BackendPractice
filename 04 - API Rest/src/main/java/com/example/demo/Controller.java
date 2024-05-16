package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Controller {
    @Autowired
    private StudentsRepository studentsRepository;

    @GetMapping("/")
    public List<String> index(){
        List<RestApiDatabase> Estudiantes = studentsRepository.findAll();
        return Estudiantes.stream().map(RestApiDatabase::getName).collect(Collectors.toList());
    }
}
