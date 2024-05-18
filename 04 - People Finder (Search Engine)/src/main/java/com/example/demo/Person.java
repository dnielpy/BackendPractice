package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Person {
    @Id
    private String id;
    private String name;
    private String age;
    private String country;
    private String color;
    private String gender;
    private String phone;


    //Constructors
    public Person(){

    }
    public Person(String Id, String Name, int Age, String Country, String Color, String Gender, String Phone) {
        this.id = Id;
        this.name = Name;
        this.age = String.valueOf(Age);
        this.country = Country;
        this.color = Color;
        this.gender = Gender;
        this.phone = Phone;
    }

    //Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getCountry() {
        return country;
    }

    public String getColor() {
        return color;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    //Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
