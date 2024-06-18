package com.example.demo;

import org.apache.catalina.User;

public interface UserService {
    Users findByName(String name);
    Users save (UserDTO userDTO);
}
