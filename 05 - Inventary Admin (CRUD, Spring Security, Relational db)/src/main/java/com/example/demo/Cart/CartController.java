package com.example.demo.Cart;

import com.example.demo.Product.ProductDTO;
import com.example.demo.User.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    CartService cartService;

    @PostMapping
    public ResponseEntity<CartEntity> createCart(@RequestBody UserDTO userDTO) {
        try {
            CartEntity cartEntity = cartService.createCart(userDTO);
            return new ResponseEntity<>(cartEntity, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<CartEntity> addToCart(@RequestBody UserDTO userDTO, @RequestBody ProductDTO productDTO) {
        try {
            CartEntity cartEntity = cartService.addToCart(userDTO, productDTO);
            return new ResponseEntity<>(cartEntity, HttpStatus.OK);
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