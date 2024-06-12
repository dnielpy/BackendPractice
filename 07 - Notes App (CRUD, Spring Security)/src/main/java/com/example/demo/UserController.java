package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotesRepository noteRepository;
    @Autowired
    private ListRepository listRepository;

    //Users CRUD
    @PostMapping
    public String createUser(@RequestParam String username, @RequestParam String password){
        UserService userService = new UserService(username, password, listRepository, noteRepository, userRepository);
        return  userService.crateUser();
    }

    @GetMapping
    public String getUser(@RequestParam String username){
        UserService userService = new UserService(username, listRepository, noteRepository, userRepository);
        return userService.getUser();
    }

//    @PutMapping
//    public String updateUser(@RequestParam String username, @RequestParam String new_username, @RequestParam String new_password){
//        UserService userService = new UserService(username, listRepository, noteRepository, userRepository);
//        return userService.updateUser(new_username, new_password);
//    }

    @DeleteMapping
    public String deleteUser(@RequestParam String username, @RequestParam String password) {
        UserService userService = new UserService(username, password, listRepository, noteRepository, userRepository);
        return userService.deleteUser();
    }
}
