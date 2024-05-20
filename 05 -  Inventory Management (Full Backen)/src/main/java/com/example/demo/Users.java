package com.example.demo;

import jakarta.persistence.Entity;

@Entity
public class Users {
    private String name;
    private String email;
    private String credit_card;

    public Users(String name, String email, String credit_card) {
        this.name = name;
        this.email = email;
        this.credit_card = credit_card;
    }

    //Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCredit_card() {
        return credit_card;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCredit_card(String credit_card) {
        this.credit_card = credit_card;
    }

    //Crear un metodo para saber si el usuario esta regisrado
    
}
