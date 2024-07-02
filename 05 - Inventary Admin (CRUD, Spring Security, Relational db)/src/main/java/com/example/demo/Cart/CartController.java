package com.example.demo.Cart;

import com.example.demo.Product.ProductDTO;
import com.example.demo.Product.ProductEntity;
import com.example.demo.Product.ProductRepository;
import com.example.demo.User.UserDTO;
import com.example.demo.User.UserEntity;
import com.example.demo.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;

    @PostMapping
    public ResponseEntity<CartEntity> createCart(@RequestBody UserDTO userDTO) {
        try {
            CartEntity cartEntity = cartService.createCart(userDTO);
            return new ResponseEntity<>(cartEntity, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addToCart")
    public ResponseEntity<List<Long>> addToCart(@RequestParam String email, @RequestParam String product_name) {
        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            UserDTO userDTO = new UserDTO(userEntity.getEmail(), userEntity.getCredit());
            ProductEntity productEntity = productRepository.findByName(product_name);
            ProductDTO productDTO = new ProductDTO(productEntity.getName(), productEntity.getPrice(), productEntity.getStock());
            CartEntity cartEntity = cartService.addToCart(userDTO, productDTO);
            return new ResponseEntity<>(cartEntity.getProducts(), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/remove")
    public ResponseEntity<Void> removeFromCart(@RequestBody ProductDTO productDTO, @RequestBody CartEntity cart) {
        try {
            cartService.removeFromCart(productDTO, cart);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}