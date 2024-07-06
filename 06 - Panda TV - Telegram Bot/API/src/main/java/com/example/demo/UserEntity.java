package com.example.demo;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String userid;
    private String firstname;
    private String lastname;
    private String lenguage;

    public UserEntity() {
    }

    public UserEntity(String username, String userid, String firstname, String lastname, String lenguage) {
        this.username = username;
        this.userid = userid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.lenguage = lenguage;
    }

    public UserDTO toDTO() {
        return new UserDTO(username, firstname, lastname);
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLenguage() {
        return lenguage;
    }

    public void setLenguage(String lenguage) {
        this.lenguage = lenguage;
    }
}
