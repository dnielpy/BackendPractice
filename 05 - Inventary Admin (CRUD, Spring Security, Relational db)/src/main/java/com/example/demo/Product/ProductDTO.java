package com.example.demo.Product;

public class ProductDTO {
    final String name;
    final double price;
    final long stock;

    public ProductDTO(String name, double price, Long stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public long getStock() {
        return stock;
    }
}
