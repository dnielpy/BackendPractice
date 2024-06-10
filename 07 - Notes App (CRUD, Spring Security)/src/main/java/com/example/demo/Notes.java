package com.example.demo;

import jakarta.persistence.*;

@Entity
public class Notes {
    @Id
    @ManyToOne
    @JoinColumn(name = "id") // Use @JoinColumn instead of @Column
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
