package com.example.demo.User;

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
        if (username != null) {
            return userRepository.findByUsername(username).toDTO();
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    public UserDTO createUser(UserEntity myuser) {
        UserEntity user = userRepository.findByUsername(myuser.getUsername());
        if (user == null) {
            userRepository.save(myuser);
            return myuser.toDTO();
        } else {
            throw new IllegalArgumentException("User already exists");
        }
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
