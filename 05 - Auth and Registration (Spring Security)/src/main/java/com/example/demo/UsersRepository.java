package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository <Users, Integer> {
    //Este método es para buscar un usuario por su nombre de usuario
    Users findByUsername(String username);
}
