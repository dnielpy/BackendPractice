package com.example.demo.services;

import com.example.demo.entitys.UserEntity;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String createUser(String email, String password){
        UserEntity new_user = userRepository.findByUserName(email);
        if (new_user == null) {
            new_user = new UserEntity(email, password, 0.0);
            userRepository.save(new_user);
            return "Usuario creado con exito";
        }
        else {
            return "El email del usuario ya existe. Por favor seleccione otro";
        }
    }

}
