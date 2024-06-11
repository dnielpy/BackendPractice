package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;

public class ListService {

    private String username;
    private String tittle;
    private String note;

    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private NotesRepository noteRepository;
    @Autowired
    private ListRepository listRepository;

    public ListService(String username, String tittle, String note) {
        this.username = username;
        this.tittle = tittle;
        this.note = note;
    }

    public ListService(String username) {
        this.username = username;
    }
}
