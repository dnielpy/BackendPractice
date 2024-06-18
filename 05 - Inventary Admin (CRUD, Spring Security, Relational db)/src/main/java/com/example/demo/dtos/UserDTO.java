package com.example.demo.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class UserDTO {
    final String email;
    final long credit;

    public UserDTO(String email, long credit) {
        this.email = email;
        this.credit = credit;
    }

    public String getEmail() {
        return email;
    }

    public long getCredit() {
        return credit;
    }
}
