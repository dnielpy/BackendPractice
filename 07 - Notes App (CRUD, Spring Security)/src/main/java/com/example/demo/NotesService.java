package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

public class NotesService {
    private String username;
    private String tittle;
    private String note;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotesRepository noteRepository;
    @Autowired
    private ListRepository listRepository;

    public NotesService(String username, String tittle, String note) {
        this.username = username;
        this.tittle = tittle;
        this.note = note;
    }

    public NotesService(String tittle, String note) {
        this.tittle = tittle;
        this.note = note;
    }

    public NotesService(String tittle) {
        this.tittle = tittle;
    }

    public NotesService() {
    }

    // CRUD Methods
    public String createNote(){
        Notes notes = new Notes(username, tittle, note);
        if (noteRepository.findByTittle(tittle) == null) {
            noteRepository.save(notes);
            return "Nota guardada con exito";
        }
        else {
            return "Titulo de Nota ya existe en la base de datos";
        }
    }
    public String getNote(){
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
    public String updateNote(){
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
    public String deleteNote(){
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
