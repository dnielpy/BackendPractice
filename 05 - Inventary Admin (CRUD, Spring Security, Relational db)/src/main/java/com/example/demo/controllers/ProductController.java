package com.example.demo.controllers;

import com.example.demo.dtos.ProductDTO;
import com.example.demo.dtos.UserDTO;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.ProductService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/create")
    public ResponseEntity<ProductDTO> createProduct(@RequestParam String name, @RequestParam double price, long stock) {
        ProductService productService = new ProductService(productRepository);
        try {
            ProductDTO productDTO = productService.createProduct(name, price, stock);
            return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    public ResponseEntity<ProductDTO> getProduct(@RequestParam String name) {
        ProductService productService = new ProductService(productRepository);
        try {
            ProductDTO productDTO = productService.getProduct(name);
            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<ProductDTO> updateProduct(@RequestParam String name, @RequestParam String new_name, @RequestParam double new_price, @RequestParam long new_stock) {
        ProductService productService = new ProductService(productRepository);
        try {
            ProductDTO productDTO = productService.updateProducts(name, new_name, new_price, new_stock);
            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<ProductDTO> deleteProduct(@RequestParam String name) {
        ProductService productService = new ProductService(productRepository);
        try {
            ProductDTO productDTO = productService.deleteProduct(name);
            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
