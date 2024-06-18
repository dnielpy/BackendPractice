package com.example.demo.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ProductDTO {
    final String name;
    final long price;
    final Integer stock;

    public ProductDTO(String name, long price, Integer stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }
}
