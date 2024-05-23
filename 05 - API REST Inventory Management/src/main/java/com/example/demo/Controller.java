package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
