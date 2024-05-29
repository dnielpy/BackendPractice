package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "users") // Asegúrate de que el nombre de la tabla es correcto
public class User {
    @Id
    @Column(name = "id") // Asegúrate de que "id" es el nombre correcto de la columna en tu base de datos
    private int id;

    @Column(name = "username") // Asegúrate de que "username" es el nombre correcto de la columna en tu base de datos
    private String username;

    @Column(name = "password") // Asegúrate de que "password" es el nombre correcto de la columna en tu base de datos
    private String password;

    @Column(name = "fullname") // Asegúrate de que "fullname" es el nombre correcto de la columna en tu base de datos
    private String fullname;

    public User() {
    }

    public User(String name, String password, String fullname) {
        this.username = name;
        this.password = password;
        this.fullname = fullname;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullname='" + fullname + '\'' +
                '}';
    }
}
