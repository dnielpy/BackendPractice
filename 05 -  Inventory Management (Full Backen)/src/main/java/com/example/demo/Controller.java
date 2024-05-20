package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private UsersRepository usersRepository;
    private List<Users> users;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/loggin")
    public String checkLoggin() {
        //All users data
        users = usersRepository.findAll();

        Auth auth = new Auth("lopez", users);

        boolean checkUser = auth.CheckUser();

        if (checkUser == true) {
            return "Si Esta";
        }
        else {
            return "no esta";
        }
    }
}
