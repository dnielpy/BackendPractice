package com.example.demo.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class UserDTO {
    final String email;
    final Double credit;

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
}
