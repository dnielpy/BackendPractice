package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class Controller {
    @Autowired
    private StudentsRepository studentsRepository;

    //Get
    @GetMapping("/students")
    public String getStudent(@RequestParam int id) {
        Optional<studentsdatabase> student = studentsRepository.findById(id);
        if (student.isPresent()) {
            studentsdatabase s = student.get();
            return s.toString();
        } else {
            return "Student not found";
        }
    }

    //Post
    @PostMapping("/students")
    public String addStudent(@RequestParam String name, @RequestParam String email, @RequestParam String gender, @RequestParam String number) {
        studentsdatabase student = new studentsdatabase();
        student.setName(name);
        student.setEmail(email);
        student.setGender(gender);
        student.setNumber(number);
        studentsRepository.save(student);
        return "Student added successfully";
    }

    //Update
    @PutMapping("/students")
    public String updateStudent(@RequestParam int id, @RequestParam String name, @RequestParam String email, @RequestParam String gender, @RequestParam String number) {
        Optional<studentsdatabase> optionalStudent = studentsRepository.findById(id);

        if (optionalStudent.isPresent()) {
            studentsdatabase student = optionalStudent.get();
            student.setName(name);
            student.setEmail(email);
            student.setGender(gender);
            student.setNumber(number);
            studentsRepository.save(student);
            return "Student updated successfully" + "\n" + student.toString();
        } else {
            return "Student not found";
        }
    }

    //Delete
    @DeleteMapping("/students")
    public String deleteStudent(@RequestParam int id) {
        Optional<studentsdatabase> optionalStudent = studentsRepository.findById(id);

        if (optionalStudent.isPresent()) {
            studentsRepository.deleteById(id);
            return "Student deleted successfully";
        } else {
            return "Student not found";
        }
    }
}
