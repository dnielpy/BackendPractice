package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private UsersRepository usersRepository;
    private List<Users> users;

//    @GetMapping("/index")
//    public String index(){
//        return "index";
//    }

    @PostMapping("/loggin")
    public String checkLoggin(
            @RequestParam String email
        ) {
        //All users data
        users = usersRepository.findAll();

        String useremail = email;

        Auth auth = new Auth(useremail, users);

        boolean checkUser = auth.CheckUser();

        if (checkUser == true) {
            return "Si Esta - pasarlo a la pagina";
        }
        else {
            return "no esta - pedirle un username y una tarjeta - pasarlo a la pagina";
        }
    }
}
