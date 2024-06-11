package com.example.demo;

import jakarta.persistence.*;

@Entity
public class Notes {
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "username")
    private String username;
    @Column(name = "tittle")
    private String tittle;
    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "Users")
    private Users user;

    public Notes() {
    }

    public Notes(String username, String title, String note) {
        this.username = username;
        this.tittle = title;
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
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
