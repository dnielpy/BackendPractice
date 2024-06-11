package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class Lists {
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "username")
    private String username;
    @Column(name = "tittle")
    private String tittle;
//    @Column(name = "notes")
//    private List<Notes> notes;

    public Lists() {
    }

//    public Lists(String username, String tittle, List<Notes> notes) {
//        this.username = username;
//        this.notes = notes;
//        this.tittle = tittle;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }
}
