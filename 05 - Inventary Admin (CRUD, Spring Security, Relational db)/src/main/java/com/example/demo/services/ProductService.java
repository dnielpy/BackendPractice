package com.example.demo.services;

import com.example.demo.dtos.ProductDTO;
import com.example.demo.dtos.UserDTO;
import com.example.demo.entitys.ProductEntity;
import com.example.demo.entitys.UserEntity;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //Create
    public ProductDTO createProduct(String name, double price, long stock) {
        ProductEntity new_product = productRepository.findByName(name);
        if (new_product == null) {
            new_product = new ProductEntity(name.toLowerCase(Locale.ROOT), price, stock);
            productRepository.save(new_product);
            return new ProductDTO(new_product.getName(), price, stock);
        } else {
            throw new IllegalArgumentException("El producto ya existe en la base de datos");
        }
    }

    //Get
    public ProductDTO getProduct(String name) {
        ProductEntity new_product = productRepository.findByName(name);
        if (new_product != null) {
            return new ProductDTO(new_product.getName(), new_product.getPrice(), new_product.getStock());
        } else {
            throw new IllegalArgumentException("El producto no existe en la base de datos");
        }
    }


}
