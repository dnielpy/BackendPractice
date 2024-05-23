package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;
import java.util.Locale;

@Entity
public class Users {
    @Id
    private String name;
    private String email;
    private String creditcard;

    public Users(){

    }

    public Users(String name, String email, String creditcard) {
        this.name = name;
        this.email = email;
        this.creditcard = creditcard;
    }

    //Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getcreditcard() {
        return creditcard;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setcreditcard(String creditcard) {
        this.creditcard = creditcard;
    }
}
