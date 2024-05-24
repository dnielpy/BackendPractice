package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Cart {
    @Id
    private int id;
    private String products;

    public Cart() {
    }

    public Cart(int id, String products) {
        this.id = id;
        this.products = products;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }
}
