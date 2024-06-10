package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notes")
public class NotesController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotesRepository noteRepository;
    @Autowired ListRepository listRepository;

    //Notes CRUD
    @PostMapping
    public String createNote(@RequestParam String username, @RequestParam String tittle, @RequestParam String note){
        Notes notes = new Notes(username, tittle, note);
        if (noteRepository.findByTittle(tittle) == null) {
            noteRepository.save(notes);
            return "Nota guardada con exito";
        }
        else {
            return "Titulo de Nota ya existe en la base de datos";
        }
    }
    @GetMapping
    public String getNote(@RequestParam String tittle){
        Notes notes = noteRepository.findByTittle(tittle);
        if (notes == null) {
            return "La nota no existe";
        }
        else {
            //Rellenar esto
            String note_info = "Tittle: \n " + notes.getTittle() + "\nNote: " + notes.getNote();
            return note_info;
        }
    }
    @PutMapping
    public String updateNote(@RequestParam String tittle, @RequestParam String note){
        Notes notes = noteRepository.findByTittle(tittle);
        if (notes == null) {
            return "La nota no existe";
        }
        else {
            notes.setTittle(tittle);
            notes.setNote(note);
            noteRepository.save(notes);
            return "Nota actualizada con exito";
        }
    }
    @DeleteMapping
    public String deleteNote(@RequestParam String tittle){
        Notes notes = noteRepository.findByTittle(tittle);
        if (notes == null) {
            return "La nota no existe";
        }
        else {
            noteRepository.delete(notes);
            return "Nota eliminada con exito";
        }
    }

}
