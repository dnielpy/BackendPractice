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

    //Update
    public ProductDTO updateProducts(String name, String new_name, double new_price, long new_stock) {
        ProductEntity product = productRepository.findByName(name);
        if (product != null) {
            if (productRepository.findByName(new_name) != null) {
                throw new IllegalArgumentException("Ya existe un producto con ese nombre en la base de datos");
            } else {
                product.setName(new_name);
                product.setPrice(new_price);
                product.setStock(new_stock);
                productRepository.save(product);
                return new ProductDTO(new_name, new_price, new_stock);
            }
        } else {
            throw new IllegalArgumentException("El producto no existe en la base de datos");
        }
    }

    //Update Stock
    public ProductDTO updateProductsStock(String name, long new_stock) {
        ProductEntity product = productRepository.findByName(name);
        if (product != null) {
                product.setStock(new_stock);
                productRepository.save(product);
                return new ProductDTO(name, product.getPrice(), new_stock);
        } else {
            throw new IllegalArgumentException("El producto no existe en la base de datos");
        }
    }

    //Delete
    public ProductDTO deleteProduct(String name) {
        ProductEntity new_product = productRepository.findByName(name);
        if (new_product != null) {
            productRepository.deleteById(new_product.getId());
            return null;
            //En el controlador controllar cuando mande null
        } else {
            throw new IllegalArgumentException("El producto no existe en la base de datos");
        }
    }


}
