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

    @GetMapping("/index")
    public String index(){
        return "index";
    }

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

    //AddUser
    @GetMapping("/adduser")
    public String adduser(){
        return "adduser.html";
    }

    @PostMapping("/adduser")
    public String adduser(@RequestParam String name, @RequestParam String email, @RequestParam String creditcard) {
//    public String addStudent() {
//        Users usuario = new Users(name, email, creditcard);
        Users usuario = new Users(name, email, creditcard);

        usuario.setName(name);
        usuario.setEmail(email);
        usuario.setcreditcard(creditcard);

       usersRepository.save(usuario);
        return "Student added successfully";
    }
}
