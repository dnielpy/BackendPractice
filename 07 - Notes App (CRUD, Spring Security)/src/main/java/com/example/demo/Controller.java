package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/createUser")
    public String createUser(@RequestParam String username, @RequestParam String password){
        Users user = new Users(username, password);
        userRepository.save(user);
        return "Usuario guardado con exito";
    }
}
