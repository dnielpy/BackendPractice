package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Users {
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "username")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "isadmin")
    private Boolean isadmin;

    //Constructors
    public Users() {
    }

    public Users(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    //Getters and setters
    public long getId() {
        return id;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getIsadmin() {
        return isadmin;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
