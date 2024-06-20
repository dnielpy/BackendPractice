package com.example.demo.services;

import com.example.demo.dtos.ProductDTO;
import com.example.demo.entitys.ProductEntity;
import org.apache.catalina.User;
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

    //Update
    public UserDTO updateUser(String email, String new_email, String new_password) {
        UserEntity user = userRepository.findByEmail(email);
        if (user != null) {
            if (userRepository.findByEmail(new_email) != null) {
                throw new IllegalArgumentException("Ya existe un usuario con ese email en la base de datos");
            } else {
                user.setEmail(new_email);
                user.setPassword(passwordEncoder().encode(new_password));
                user.setCredit(0.00);
                userRepository.save(user);
                return new UserDTO(new_email, 0.00);
            }
        } else {
            throw new IllegalArgumentException("El usuario no existe en la base de datos");
        }
    }

    //Delete
    public UserDTO deleteUser(String email) {
        UserEntity new_user = userRepository.findByEmail(email);
        if (new_user != null) {
            userRepository.deleteById(new_user.getId());
            return null;
        } else {
            throw new IllegalArgumentException("El usuario no existe en la base de datos");
        }
    }
}
