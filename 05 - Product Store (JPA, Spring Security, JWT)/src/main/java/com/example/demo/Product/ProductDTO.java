package com.example.demo.Product;

import com.example.demo.Category.CategoryEntity;

import java.math.BigDecimal;

public class ProductDTO {
    final String name;
    final double cost;
    final String longDescription;
    final String shortDescription;
    final long stock;
    final String image;
    final CategoryEntity category;

    public ProductDTO(String name, double cost, String longDescription, String shortDescription, Long stock, String image, CategoryEntity category) {
        this.name = name;
        this.cost = cost;
        this.longDescription = longDescription;
        this.shortDescription = shortDescription;
        this.stock = stock;
        this.image = image;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public long getStock() {
        return stock;
    }

    public String getImage() {
        return image;
    }

    public CategoryEntity getCategory() {
        return category;
    }
}