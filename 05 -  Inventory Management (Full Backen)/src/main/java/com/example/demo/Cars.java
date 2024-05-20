package com.example.demo;

public class Cars {
    private String model;
    private String price;

    public Cars(String model, String price) {
        this.model = model;
        this.price = price;
    }

    //Getters
    public String getmodel() {
        return model;
    }

    public String getEmail() {
        return price;
    }

    //Setters
    public void setmodel(String model) {
        this.model = model;
    }

    public void setEmail(String email) {
        this.price = email;
    }
}
