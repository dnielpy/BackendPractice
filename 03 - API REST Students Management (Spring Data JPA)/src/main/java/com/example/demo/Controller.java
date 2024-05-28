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

    //Sorry for this
    String getHTMLStructure(String name,String email,String gender,String number){
        return "<html>" +
                "<body>" +
                "<h1>Student Details</h1>" +
                "<p><strong>Name:</strong> " + name + "</p>" +
                "<p><strong>Email:</strong> " + email + "</p>" +
                "<p><strong>Gender:</strong> " + gender + "</p>" +
                "<p><strong>Number:</strong> " + number + "</p>" +
                "</body>" +
                "</html>";
    }

    //Get
    @GetMapping("/students")
    public String getStudent(@RequestParam int id) {
        Optional<studentsdatabase> student = studentsRepository.findById(id);
        if (student.isPresent()) {
            studentsdatabase s = student.get();
            return getHTMLStructure(s.getName(), s.getEmail(), s.getGender(), s.getNumber());
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

    //Put
    @PutMapping("/students")
    public String updateStudent(@RequestParam int id, @RequestBody studentsdatabase updatedStudent) {
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
