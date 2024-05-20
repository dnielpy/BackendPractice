package com.example.demo;

import org.springframework.stereotype.Repository;

public class UserTesting {
    String username = "daniel";
    private UsersRepository usersRepository;

    public String SearchUserTesting(){
        Users usuarios = new Users(usersRepository);
    }

}

