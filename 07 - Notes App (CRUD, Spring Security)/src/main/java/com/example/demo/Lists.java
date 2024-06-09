package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class Lists {
    @Id
    private String username;
    private String tittle;
    private List<Notes> notes;

    public Lists() {
    }

    public Lists(String username, String tittle, List<Notes> notes) {
        this.username = username;
        this.notes = notes;
        this.tittle = tittle;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Notes> getNotes() {
        return notes;
    }

    public void setNotes(List<Notes> notes) {
        this.notes = notes;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }
}
