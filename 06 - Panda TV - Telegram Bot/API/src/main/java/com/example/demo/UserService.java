package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserDTO getUser(String username) {
        return userRepository.findByUsername(username).toDTO();
    }

    public UserDTO createUser(UserEntity myuser) {
        UserEntity user = userRepository.findByUsername(myuser.getUsername());
        userRepository.save(user);
        return user.toDTO();
    }

    public UserDTO updateUser(UserEntity myuser) {
        UserEntity user = userRepository.findByUsername(myuser.getUsername());
        userRepository.save(user);
        return user.toDTO();
    }

    public void deleteUser(String username) {
        UserEntity user = userRepository.findByUsername(username);
        userRepository.delete(user);
    }
}
