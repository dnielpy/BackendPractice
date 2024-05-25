package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Admins {
    @Id
    private int id;
    private String name;

}
