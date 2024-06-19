package com.example.demo.controllers;

import com.example.demo.dtos.UserDTO;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestParam String email, @RequestParam String password) {
        UserService userService = new UserService(userRepository);
        try {
            UserDTO userDTO = userService.createUser(email, password);
            return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }
    @GetMapping
    public ResponseEntity<UserDTO> getUser(@RequestParam String email) {
        UserService userService = new UserService(userRepository);
        try {
            UserDTO userDTO = userService.getUser(email);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
