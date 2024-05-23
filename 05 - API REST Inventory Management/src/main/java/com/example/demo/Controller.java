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
    private ProductReository productsRepository;

    @GetMapping("/viewProducts")
    public List<Products> viewProducts(){
        List<Products> products = productsRepository.findAll();
        return products;
    }

    @PostMapping("/viewProducts")
    public Products getPreoduct(@RequestParam int id){
        Products product = productsRepository.findById(id).orElse(null);
        return product;
    }
}
