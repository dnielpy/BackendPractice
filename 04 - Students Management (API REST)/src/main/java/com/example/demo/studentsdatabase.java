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

    @Override
    public String toString() {
        //devolver su nombre, email, género y número de teléfono
        return "Name: " + name + "\nEmail: " + email + " \nGender: " + gender + "\nNumber: " + number;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public studentsdatabase() {
    }

    public studentsdatabase(int id, String name, String email, String gender, String number) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.number = number;
    }

}
