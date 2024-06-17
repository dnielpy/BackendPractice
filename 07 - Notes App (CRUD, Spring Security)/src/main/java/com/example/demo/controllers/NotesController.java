package com.example.demo.controllers;

import com.example.demo.repositories.ListRepository;
import com.example.demo.repositories.NotesRepository;
import com.example.demo.services.NotesService;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/notes")
public class NotesController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotesRepository noteRepository;
    @Autowired
    ListRepository listRepository;

    @PostMapping
    public String createNote(@RequestParam String tittle, @RequestParam String note, Principal principal) {
        NotesService notesService = new NotesService(principal.getName(), tittle, note, userRepository, noteRepository, listRepository);
        return notesService.createNote();
    }

    @GetMapping()
    public String getNote(@RequestParam String tittle, Principal principal) {
        NotesService notesService = new NotesService(tittle, principal.getName(), userRepository, noteRepository, listRepository);
        return notesService.getNote();
    }

    @PutMapping
    public String updateNote(@RequestParam String tittle, @RequestParam String note) {
        NotesService notesService = new NotesService(tittle, note, userRepository, noteRepository, listRepository);
        return notesService.updateNote();
    }

    @DeleteMapping
    public String deleteNote(@RequestParam String tittle, Principal principal) {
        NotesService notesService = new NotesService(tittle, principal.getName(), userRepository, noteRepository, listRepository);
        return notesService.getNote();
    }
}
