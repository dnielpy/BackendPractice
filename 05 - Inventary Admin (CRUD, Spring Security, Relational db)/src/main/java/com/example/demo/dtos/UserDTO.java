package com.example.demo.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class UserDTO {
    final String email;
    final Double credit;
    private String token;

    public UserDTO(String email, Double credit, String token) {
        this.email = email;
        this.credit = credit;
        this.token = token;
    }

    public UserDTO(String email, Double credit) {
        this.email = email;
        this.credit = credit;
    }

    public String getEmail() {
        return email;
    }

    public Double getCredit() {
        return credit;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
