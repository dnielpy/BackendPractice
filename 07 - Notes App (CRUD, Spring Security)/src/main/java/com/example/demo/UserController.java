package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotesRepository noteRepository;
    @Autowired ListRepository listRepository;

    //Users CRUD
    @PostMapping
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
    @GetMapping
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
    @PutMapping
    public String updateUser(@RequestParam String username, @RequestParam String password){
        Users user = userRepository.findByUserName(username);
        if (user == null) {
            return "El usuario no existe";
        }
        else {
            user.setUsername(username);
            user.setPassword(password);
            userRepository.save(user);
            return "Usuario actualizado con exito";
        }
    }
    @DeleteMapping
    public String deleteUser(@RequestParam String username, @RequestParam String password){
        Users user = userRepository.findByUserName(username);
        if (user == null) {
            return "El usuario no existe";
        }
        else {
            if (user.getPassword().equals(password)) {
                userRepository.delete(user);
                return "Usuario eliminado con exito";
            }
            else {
                return "Contrasenna incorrecta";
            }
        }
    }

}
