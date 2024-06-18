package com.example.demo.entitys;

import jakarta.persistence.*;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "credit")
    private long credit;

    public UserEntity(long id, String email, String password, long credit) {
        this.email = email;
        this.password = password;
        this.credit = credit;
    }

    public UserEntity() {
    }

}
