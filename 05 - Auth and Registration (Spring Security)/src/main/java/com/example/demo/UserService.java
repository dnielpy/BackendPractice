package com.example.demo;

public interface UserService {
    Users findByName(String name);
    Users save (UserDTO userDTO);
}
