package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

public class UserService {
    private String username;
    private String password;

    private UserRepository userRepository;
    private NotesRepository noteRepository;
    private ListRepository listRepository;

    public UserService(String username, String password, ListRepository listRepository, NotesRepository noteRepository, UserRepository userRepository) {
        this.username = username.toLowerCase(Locale.ROOT);
        this.password = password;
        this.listRepository = listRepository;
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    public UserService( String username, ListRepository listRepository, NotesRepository noteRepository, UserRepository userRepository) {
        this.username = username.toLowerCase(Locale.ROOT);
        this.listRepository = listRepository;
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    public UserService() {
    }

    public String crateUser(){
        Users user = new Users(username, password);
        if (userRepository.findByUserName(username) == null) {
            userRepository.save(user);
            return "Usuario guardado con exito";
        }
        else {
            return "Nombre de Usuario ya existe en la base de datos";
        }
    }

    public String getUser(){
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
    public String updateUser(){
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
    public String deleteUser(){
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
