package com.example.demo.services;

import com.example.demo.repositories.ListRepository;
import com.example.demo.repositories.NotesRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.entitys.Users;

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

    public UserService(String username, ListRepository listRepository, NotesRepository noteRepository, UserRepository userRepository) {
        this.username = username.toLowerCase(Locale.ROOT);
        this.listRepository = listRepository;
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    public UserService() {
    }

    public String crateUser() {
        Users user = new Users(username, password);
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
        } else {
            //Rellenar esto
            String user_info = "Name: \n " + user.getUsername() + "\nNotes: ";
            return user_info;
        }
    }
//    public String updateUser(String new_username, String new_password) {
//        Users existingUser = userRepository.findByUserName(username);
//        if (existingUser == null) {
//            return "El usuario no existe";
//        } else {
//            Users userWithNewUsername = userRepository.findByUserName(new_username);
//            if (userWithNewUsername != null) {
//                return "El nuevo nombre de usuario ya existe";
//            }
//            existingUser.setUsername(new_username);
//            existingUser.setPassword(new_password);
//            userRepository.save(existingUser);
//            return "Usuario actualizado con exito";
//        }
//    }

    public String deleteUser() {
        Users user = userRepository.findByUserName(username);
        if (user == null) {
            return "El usuario no existe";
        } else {
            long id = user.getId();
            userRepository.deleteById(id);
            return "Usuario eliminado con exito";
        }
    }

//    public UserDetailsService users(PasswordEncoder passwordEncoder) {
//        User.UserBuilder user = User.builder();
//
//        List<Users> users_in_bd = userRepository.findAll();
//
//        for (int i = 0; i < users_in_bd.size(); i++) {
//                    UserDetails users = user.username(users_in_bd.get(i).getUsername())
//                    .password(passwordEncoder.encode(users_in_bd.get(i).getPassword()))
//                    .roles()
//                    .build();
//        }
//        return new InMemoryUserDetailsManager(users);
//    }
}
