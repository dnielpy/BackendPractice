package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Notes {
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "username")
    private String username;
    @Column(name = "title")
    private String title;
    @Column(name = "note")
    private String note;

    public Notes() {
    }

    public Notes(String username, String title, String note) {
        this.username = username;
        this.title = title;
        this.note = note;
    }

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

    public String getTittle() {
        return title;
    }

    public void setTittle(String tittle) {
        this.title = tittle;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
