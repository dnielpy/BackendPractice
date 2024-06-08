package com.example.demo;

import org.apache.catalina.User;
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
        if (userRepository.findByUserName(username) == null) {
            userRepository.save(user);
            return "Usuario guardado con exito";
        }
        else {
            return "Nombre de Usuario ya existe en la base de datos";
        }
    }

    @GetMapping("/getUser")
    public String getUser(@RequestParam String username){
        Users user = userRepository.findByUserName(username);

        if (user == null) {
            return "El usuario no existe";
        }
        else {
            //Rellenar esto
            String user_info = "Name: \n " + user.getUsername() + "\nNotes: ";
            return user_info;
        }
    }
}
