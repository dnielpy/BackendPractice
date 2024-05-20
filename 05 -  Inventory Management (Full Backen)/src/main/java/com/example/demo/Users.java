package com.example.demo;

import jakarta.persistence.Entity;

import java.util.List;
import java.util.Locale;

@Entity
public class Users {
    private String name;
    private String email;
    private String credit_card;
    List<Users> user_data;

    public Users(String name, String email, String credit_card, List<Users> User_data) {
        this.name = name;
        this.email = email;
        this.credit_card = credit_card;
        this.user_data = User_data;
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
    public boolean SearchUser(String username) {
        for (int i = 0; i < this.user_data.size(); i++) {
            if (this.user_data.get(i).getName().toLowerCase(Locale.ROOT).equals(username.toLowerCase(Locale.ROOT))) {
                return true;
            }
        }
        return false;
    }
}
