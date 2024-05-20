package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Food {
    @Id
    private String name;
    private String price;

    public Food(String name, String price) {
        this.name = name;
        this.price = price;
    }

    //Getters
    public String getmodel() {
        return name;
    }

    public String getEmail() {
        return price;
    }

    //Setters
    public void setmodel(String model) {
        this.name = model;
    }

    public void setEmail(String email) {
        this.price = email;
    }
}
