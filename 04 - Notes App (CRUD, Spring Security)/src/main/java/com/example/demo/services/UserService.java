package com.example.demo.services;

import com.example.demo.repositories.ListRepository;
import com.example.demo.repositories.NotesRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.entitys.Users;

import java.util.Locale;
import java.util.Objects;

public class UserService {
    private String username;
    private String password;
    private String logged_username;

    private UserRepository userRepository;
    private NotesRepository noteRepository;
    private ListRepository listRepository;

    public UserService(String username, String password, String logged_username, ListRepository listRepository, NotesRepository noteRepository, UserRepository userRepository) {
        this.username = username.toLowerCase(Locale.ROOT);
        this.password = password;
        this.logged_username = logged_username;
        this.listRepository = listRepository;
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }


    public UserService(String username, String password, UserRepository userRepository, NotesRepository noteRepository, ListRepository listRepository) {
        this.username = username;
        this.password = password;
        this.userRepository = userRepository;
        this.noteRepository = noteRepository;
        this.listRepository = listRepository;
    }

    //Get user
    public UserService(String username, String logged_username, ListRepository listRepository, NotesRepository noteRepository, UserRepository userRepository) {
        this.username = username.toLowerCase(Locale.ROOT);
        this.logged_username = logged_username;
        this.listRepository = listRepository;
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }


    public UserService() {
    }

    public String crateUser() {
        Users user = new Users(username, password, false);
        if (userRepository.findByUserName(username) == null) {
            userRepository.save(user);
            return "Usuario guardado con exito";
        } else {
            return "Nombre de Usuario ya existe en la base de datos";
        }
    }

    public String getUser() {
        Users user = userRepository.findByUserName(username);
        if (user == null) {
            return "El usuario no existe";
        }
        if (!Objects.equals(user.getUsername(), logged_username)) {
            return "No tienes acceso a este usuario";
        } else {
            //Rellenar esto
            String user_info = "Name: \n " + user.getUsername() + "\nNotes: ";
            return user_info;
        }
    }

    public String updateUser(String new_username, String new_password, String principal) {
        Users existingUser = userRepository.findByUserName(username);
        if (existingUser == null) {
            return "El usuario no existe";
        }
        if (!Objects.equals(existingUser.getUsername(), principal)) {
            return "No tienes acceso a esta nota";
        } else {
            Users userWithNewUsername = userRepository.findByUserName(new_username);
            if (userWithNewUsername != null) {
                return "El nuevo nombre de usuario ya existe";
            }
            existingUser.setUsername(new_username);
            existingUser.setPassword(new_password);
            userRepository.save(existingUser);
            return "Usuario actualizado con exito";
        }
    }

    public String deleteUser() {
        Users user = userRepository.findByUserName(username);
        if (user == null) {
            return "El usuario no existe";
        }
        if (!Objects.equals(user.getUsername(), logged_username)) {
            return "No tienes acceso a este usuario";
        } else {
            long id = user.getId();
            userRepository.deleteById(id);
            return "Usuario eliminado con exito";
        }
    }
}
