package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Clients {
    @Id
    private int id;
    private String name;
    private Integer balance;
    private String products;

    public Clients() {
    }

    public Clients(int id, String name, Integer balance, String products) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Clients{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", products='" + products + '\'' +
                '}';
    }
}
