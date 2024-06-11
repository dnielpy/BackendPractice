package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notes")
public class NotesController {
    @PostMapping
    public String createNote(@RequestParam String username, @RequestParam String tittle, @RequestParam String note){
        NotesService notesService = new NotesService(username, tittle, note);
        return notesService.createNote();
    }
    @GetMapping
    public String getNote(@RequestParam String tittle){
        NotesService notesService = new NotesService(tittle);
        return notesService.getNote();
    }
    @PutMapping
    public String updateNote(@RequestParam String tittle, @RequestParam String note){
        NotesService notesService = new NotesService(tittle, note);
        return notesService.updateNote();
    }
    @DeleteMapping
    public String deleteNote(@RequestParam String tittle){
        NotesService notesService = new NotesService(tittle);
        return notesService.getNote();
    }

}
