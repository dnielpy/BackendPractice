package com.example.demo.User;

import com.example.demo.Cart.CartEntity;
import com.example.demo.Product.ProductRepository;
import com.example.demo.Sale.SaleDTO;
import com.example.demo.Sale.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private ProductRepository productRepository;


    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestParam String email, @RequestParam String password) {
        try {
            UserDTO userDTO = userService.createUser(email, password);
            return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String email) {
        try {
            UserDTO userDTO = userService.getUser(email);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{email}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String email, @RequestParam String new_email, @RequestParam String new_password) {
        try {
            UserDTO userDTO = userService.updateUser(email, new_email, new_password);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{email}/credit")
    public ResponseEntity<UserDTO> updateUserCredit(@PathVariable String email, @RequestParam double new_credit) {
        try {
            UserDTO userDTO = userService.updateUserCredit(email, new_credit);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable String email) {
        try {
            userService.deleteUser(email);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{email}/buy")
    public ResponseEntity<SaleDTO> buy(@PathVariable String email, @RequestBody CartEntity cart) {
        try {
            UserDTO userDTO = userService.getUser(email);
            SaleDTO saleDTO = userService.buy(userDTO, cart);
            return new ResponseEntity<>(saleDTO, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
