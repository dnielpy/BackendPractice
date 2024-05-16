package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class Controller {
    @Autowired
    private StudentsRepository studentsRepository;

    @GetMapping("/names/{id}")
    public String getName(@PathVariable int id) {
        Optional<studentsdatabase> student = studentsRepository.findById(id);
        if (student.isPresent()) {
            return student.get().getName();
        } else {
            return "Student not found";
        }
    }
@GetMapping("/emails/{id}")
public String getEmail(@PathVariable int id) {
    Optional<studentsdatabase> student = studentsRepository.findById(id);
    if (student.isPresent()) {
        return student.get().getEmail();
    } else {
        return "Student not found";
    }
}

@GetMapping("/genders/{id}")
public String getGender(@PathVariable int id) {
    Optional<studentsdatabase> student = studentsRepository.findById(id);
    if (student.isPresent()) {
        return student.get().getGender();
    } else {
        return "Student not found";
    }
}

@GetMapping("/numbers/{id}")
public String getNumber(@PathVariable int id) {
    Optional<studentsdatabase> student = studentsRepository.findById(id);
    if (student.isPresent()) {
        return student.get().getNumber();
    } else {
        return "Student not found";
    }
}
}
