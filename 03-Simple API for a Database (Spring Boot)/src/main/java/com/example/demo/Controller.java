package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Controller {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/names")
    public List<String> getNames() {
        List<Data> people = personRepository.findAll();
        return people.stream().map(Data::getName).collect(Collectors.toList());
    }

    @GetMapping("/emails")
    public List<String> getEmails() {
        List<Data> people = personRepository.findAll();
        return people.stream().map(Data::getEmail).collect(Collectors.toList());
    }

    @GetMapping("/genders")
    public List<String> getGenders() {
        List<Data> people = personRepository.findAll();
        return people.stream().map(Data::getGender).collect(Collectors.toList());
    }

    @GetMapping("/numbers")
    public List<String> getNumbers() {
        List<Data> people = personRepository.findAll();
        return people.stream().map(Data::getNumber).collect(Collectors.toList());
    }
}