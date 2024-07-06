package com.example.demo.User;

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
