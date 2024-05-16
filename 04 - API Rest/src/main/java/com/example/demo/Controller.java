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

    //Get
    @GetMapping("/students/{id}")
    public String getStudent(@PathVariable int id) {
        Optional<studentsdatabase> student = studentsRepository.findById(id);
        if (student.isPresent()) {
            return student.get().toString();
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

    //Put
    @PutMapping("/students/{id}")
    public String updateStudent(@PathVariable int id, @RequestBody studentsdatabase updatedStudent) {
        Optional<studentsdatabase> optionalStudent = studentsRepository.findById(id);

        if (optionalStudent.isPresent()) {
            studentsdatabase student = optionalStudent.get();
            student.setName(updatedStudent.getName());
            student.setEmail(updatedStudent.getEmail());
            student.setGender(updatedStudent.getGender());
            student.setNumber(updatedStudent.getNumber());
            studentsRepository.save(student);
            return "Student updated successfully";
        } else {
            return "Student not found";
        }
    }

    //Delete
    //Delete
    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable int id) {
        Optional<studentsdatabase> optionalStudent = studentsRepository.findById(id);

        if (optionalStudent.isPresent()) {
            studentsRepository.deleteById(id);
            return "Student deleted successfully";
        } else {
            return "Student not found";
        }
    }

}
