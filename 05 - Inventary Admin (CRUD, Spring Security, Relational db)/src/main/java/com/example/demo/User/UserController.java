package com.example.demo.User;

import com.example.demo.Admin.AdminDTO;
import com.example.demo.Admin.AdminService;
import com.example.demo.Product.ProductService;
import com.example.demo.Sale.SaleDTO;
import com.example.demo.Sale.SaleRepository;
import com.example.demo.Sale.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserRepository userRepository;
    private ProductService productService;
    private UserService userService;
    private SaleRepository saleRepository;

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
