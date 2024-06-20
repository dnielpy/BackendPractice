package com.example.demo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.dtos.UserDTO;
import com.example.demo.entitys.UserEntity;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Locale;

public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public UserDTO createUser(String email, String password) {
        UserEntity new_user = userRepository.findByEmail(email);
        if (new_user == null) {
            new_user = new UserEntity(email.toLowerCase(Locale.ROOT), passwordEncoder().encode(password), 0.0);
            userRepository.save(new_user);
            return new UserDTO(new_user.getEmail(), 0.00);
        } else {
            throw new IllegalArgumentException("El email ya existe en la base de datos. Seleccione otro");
        }
    }

    public UserDTO getUser(String email) {
        UserEntity new_user = userRepository.findByEmail(email);
        if (new_user != null) {
            return new UserDTO(new_user.getEmail(), new_user.getCredit());
        } else {
            throw new IllegalArgumentException("El usuario no existe en la base de datos");
        }
    }
}
