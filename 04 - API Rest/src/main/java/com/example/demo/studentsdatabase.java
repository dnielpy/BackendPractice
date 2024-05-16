package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class studentsdatabase {
    @Id
    private int id;
    private String name;
    private String email;
    private String gender;
    private String number;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getNumber() {
        return number;
    }

}
