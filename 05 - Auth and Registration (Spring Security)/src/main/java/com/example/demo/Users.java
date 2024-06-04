package com.example.demo;

import jakarta.persistence.Entity;

@Entity
public class Users {
    private long id;
    private String username;
    private String password;

    //Constructors
    public Users() {
    }

    public Users(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    //Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //ToString
    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
