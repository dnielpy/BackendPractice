package com.example.demo.entitys;

import jakarta.persistence.*;

@Entity
public class Users {
    //Hcaer que el campo id sea autoincrementable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Users(String username, String password, boolean isadmin) {
        this.userName = username;
        this.password = password;
        this.isadmin = isadmin;
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

    public void setIsadmin(Boolean isadmin) {
        this.isadmin = isadmin;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
