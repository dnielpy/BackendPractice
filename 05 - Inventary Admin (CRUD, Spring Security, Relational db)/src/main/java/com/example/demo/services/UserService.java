package com.example.demo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.dtos.UserDTO;
import com.example.demo.entitys.UserEntity;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;

public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO createUser(String email, String password) {
        try {
            UserEntity new_user = userRepository.findByEmail(email);
            if (new_user == null) {
                new_user = new UserEntity(email.toLowerCase(Locale.ROOT), password, 0.0);
                userRepository.save(new_user);
                return new UserDTO(new_user.getEmail(), 0.00);
            }
        } catch (Exception e) {
            logger.error("El email ya existe en la base de datos. Seleccione otro", e);
        }
        return null;
    }

    public UserDTO getUser(String email) {
        try {
            UserEntity new_user = userRepository.findByEmail(email);
            if (new_user != null) {
                return new UserDTO(new_user.getEmail(), new_user.getCredit());
            }
        } catch (Exception e) {
            logger.error("El usuario no existe en la base de datos", e);
        }
        return null;
    }
}
