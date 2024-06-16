package com.example.demo.services;

import com.example.demo.repositories.ListRepository;
import com.example.demo.Notes;
import com.example.demo.repositories.NotesRepository;
import com.example.demo.repositories.UserRepository;

import java.util.Locale;

public class NotesService {
    private String username;
    private String tittle;
    private String note;

    private UserRepository userRepository;
    private NotesRepository noteRepository;
    private ListRepository listRepository;

    public NotesService(String username, String tittle, String note, UserRepository userRepository, NotesRepository noteRepository, ListRepository listRepository) {
        this.username = username.toLowerCase(Locale.ROOT);
        this.tittle = tittle.toLowerCase(Locale.ROOT);
        this.note = note;
        this.userRepository = userRepository;
        this.noteRepository = noteRepository;
        this.listRepository = listRepository;
    }

    public NotesService(String tittle, String note, UserRepository userRepository, NotesRepository noteRepository, ListRepository listRepository) {
        this.tittle = tittle.toLowerCase(Locale.ROOT);
        this.note = note;
        this.userRepository = userRepository;
        this.noteRepository = noteRepository;
        this.listRepository = listRepository;
    }

    public NotesService(String tittle, UserRepository userRepository, NotesRepository noteRepository, ListRepository listRepository) {
        this.tittle = tittle.toLowerCase(Locale.ROOT);
        this.userRepository = userRepository;
        this.noteRepository = noteRepository;
        this.listRepository = listRepository;
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
