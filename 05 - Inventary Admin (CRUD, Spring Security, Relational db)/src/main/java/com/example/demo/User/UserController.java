package com.example.demo.User;

import com.example.demo.Product.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserRepository userRepository;

    private ProductService productService;

    private UserService userService;

    @GetMapping
    public ResponseEntity<UserDTO> getUser(@RequestParam String email) {
        UserService userService = new UserService(userRepository);
        try {
            UserDTO userDTO = userService.getUser(email);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
