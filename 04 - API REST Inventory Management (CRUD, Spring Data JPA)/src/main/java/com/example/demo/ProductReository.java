package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReository extends JpaRepository<Products, Integer>{
    Products findByName(String p);
}
