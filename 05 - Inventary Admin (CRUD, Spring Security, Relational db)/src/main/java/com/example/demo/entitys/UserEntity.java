package com.example.demo.entitys;

import jakarta.persistence.*;

@Entity
@Table(name = "User")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "credit")
    private Double credit;

    public UserEntity(long id, String email, String password, Double credit) {
        this.email = email;
        this.password = password;
        this.credit = credit;
    }

    public UserEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", credit=" + credit +
                '}';
    }
}
