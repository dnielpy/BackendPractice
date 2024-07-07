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


    public UserDTO getUser(String userid) {
        if (userid != null) {
            UserEntity user = userRepository.findByUserid(userid);
            if (user != null) {
                return user.toDTO();
            } else {
                throw new IllegalArgumentException("User not found");
            }
        } else {
            throw new IllegalArgumentException("Userid cannot be null");
        }
    }

    public UserDTO createUser(UserEntity myuser) {
        UserEntity user = userRepository.findByUserid(myuser.getUserid());
        if (user == null) {
            userRepository.save(myuser);
            return myuser.toDTO();
        } else {
            throw new IllegalArgumentException("User already exists");
        }
    }

    public UserDTO updateUser(UserEntity myuser) {
        UserEntity user = userRepository.findByUserid(myuser.getUserid());
        userRepository.save(user);
        return user.toDTO();
    }

    public void deleteUser(String userid) {
        UserEntity user = userRepository.findByUserid(userid);
        userRepository.delete(user);
    }
}
