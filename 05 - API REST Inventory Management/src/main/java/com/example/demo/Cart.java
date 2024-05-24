package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Cart {
    @Id
    private int clientid;
    private String products;

    public Cart() {
    }

    public Cart(int clientid, String products) {
        this.clientid = clientid;
        this.products = products;
    }

    public int getclientid() {
        return clientid;
    }

    public void setclientid(int clientid) {
        this.clientid = clientid;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }
}
