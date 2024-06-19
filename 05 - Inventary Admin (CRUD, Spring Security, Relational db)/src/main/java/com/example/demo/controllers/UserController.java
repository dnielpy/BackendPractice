package com.example.demo.controllers;

import com.example.demo.dtos.UserDTO;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public UserDTO createUser(@RequestParam String email, @RequestParam String password) {
        UserService userService = new UserService(userRepository);
        return userService.createUser(email, password);
    }

    @GetMapping
    public UserDTO getUser(@RequestParam String email) {
        UserService userService = new UserService(userRepository);
        return userService.getUser(email);
    }
}
