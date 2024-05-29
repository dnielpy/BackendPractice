package com.example.demo;

public interface UserService {
    User findByName(String name);
    User save(UserDto userDto);
}
