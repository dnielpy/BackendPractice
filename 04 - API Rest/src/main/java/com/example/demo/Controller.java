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

    @GetMapping("/names")
    public List<String> getNames() {
        List<studentsdatabase> people = studentsRepository.findAll();
        return people.stream().map(studentsdatabase::getName).collect(Collectors.toList());
    }

    @GetMapping("/emails")
    public List<String> getEmails() {
        List<studentsdatabase> people = studentsRepository.findAll();
        return people.stream().map(studentsdatabase::getEmail).collect(Collectors.toList());
    }

    @GetMapping("/genders")
    public List<String> getGenders() {
        List<studentsdatabase> people = studentsRepository.findAll();
        return people.stream().map(studentsdatabase::getGender).collect(Collectors.toList());
    }

    @GetMapping("/numbers")
    public List<String> getNumbers() {
        List<studentsdatabase> people = studentsRepository.findAll();
        return people.stream().map(studentsdatabase::getNumber).collect(Collectors.toList());
    }
}
