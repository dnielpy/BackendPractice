package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Sales {
    @Id
    private String username;
    private String products;
    private Integer cost;

    public Sales() {
    }

    public Sales(String username, String products, Integer cost) {
        this.username = username;
        this.products = products;
        this.cost = cost;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
