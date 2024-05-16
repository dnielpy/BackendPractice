package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class Controller {
    @Autowired
    private StudentsRepository studentsRepository;

    @GetMapping("/students/{id}")
    public String getStudent(@PathVariable int id) {
        Optional<studentsdatabase> student = studentsRepository.findById(id);
        if (student.isPresent()) {
            return student.get().toString();
        } else {
            return "Student not found";
        }
    }

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

    //Post
    @PostMapping("/students")
    public String addStudent(@RequestBody studentsdatabase student) {
        studentsRepository.save(student);
        return "Student added succesfully";
    }
}
