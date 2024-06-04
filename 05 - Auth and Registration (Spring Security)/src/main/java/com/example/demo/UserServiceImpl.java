package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceImpl implements UserService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Users findByName(String name){
        return userRepository.findByUserName(name);
    }

    @Override
    public Users save(UserDTO userDTO){
        Users user = new Users(userDTO.getId(), userDTO.getUsername(), passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(user);
    }
}
