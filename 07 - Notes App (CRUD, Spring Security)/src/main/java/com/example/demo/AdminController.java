package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotesRepository noteRepository;
    @Autowired
    private ListRepository listRepository;

    @DeleteMapping("/user")
    public String deleteUser(@RequestParam String username) {
        UserService userService = new UserService(username, listRepository, noteRepository, userRepository);
        return userService.deleteUser();
    }

}
