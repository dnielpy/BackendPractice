package com.example.demo.controllers;

import com.example.demo.dtos.Cart;
import com.example.demo.dtos.ProductDTO;
import com.example.demo.dtos.UserDTO;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.ProductService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

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
