package com.example.demo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    UserService userService;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //CRUD operations
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserEntity myuser) {
        try {
            UserDTO userDTO = userService.createUser(myuser);
            return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<UserDTO> getUser(@RequestParam String userid) {
        try {
            UserDTO userDTO = userService.getUser(userid);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserEntity myuser) {
        try {
            UserDTO userDTO = userService.updateUser(myuser);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestBody String userid) {
        try {
            userService.deleteUser(userid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}