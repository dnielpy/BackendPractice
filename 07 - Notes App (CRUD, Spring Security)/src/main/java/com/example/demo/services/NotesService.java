package com.example.demo.services;

import com.example.demo.entitys.Users;
import com.example.demo.repositories.ListRepository;
import com.example.demo.entitys.Notes;
import com.example.demo.repositories.NotesRepository;
import com.example.demo.repositories.UserRepository;

import java.util.Locale;
import java.util.Objects;

public class NotesService {
    private String username;
    private String tittle;
    private String note;
    private String logged_username;

    private UserRepository userRepository;
    private NotesRepository noteRepository;
    private ListRepository listRepository;

    //Constructor to createNote
    public NotesService(String username, String tittle, String note, UserRepository userRepository, NotesRepository noteRepository, ListRepository listRepository) {
        this.username = username.toLowerCase(Locale.ROOT);
        this.tittle = tittle.toLowerCase(Locale.ROOT);
        this.note = note;
        this.userRepository = userRepository;
        this.noteRepository = noteRepository;
        this.listRepository = listRepository;
    }

    public NotesService(String username, String tittle, String note, String logged_username, UserRepository userRepository, NotesRepository noteRepository, ListRepository listRepository) {
        this.username = username;
        this.tittle = tittle.toLowerCase(Locale.ROOT);
        this.note = note;
        this.logged_username = logged_username;
        this.userRepository = userRepository;
        this.noteRepository = noteRepository;
        this.listRepository = listRepository;
    }

    public NotesService(String tittle, String logged_username, UserRepository userRepository, NotesRepository noteRepository, ListRepository listRepository) {
        this.tittle = tittle.toLowerCase(Locale.ROOT);
        this.logged_username = logged_username;
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
    //Get
    public String getNote(){
        Notes notes = noteRepository.findByTittle(tittle);
        //Comprobar que el username sea el username de la nota
        if (notes == null) {
            return "La nota no existe";
        }
        if (!Objects.equals(notes.getUsername(), logged_username)) {
            return "No tienes acceso a esta nota";
        }
        else {
            return "Tittle: \n " + notes.getTittle() + "\nNote: " + notes.getNote();
        }
    }
    public String updateNote(){
        Notes notes = noteRepository.findByTittle(tittle);
        if (notes == null) {
            return "La nota no existe";
        }
        if (!Objects.equals(notes.getUsername(), logged_username)) {
            return "No tienes acceso a esta nota";
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
        if (!Objects.equals(notes.getUsername(), logged_username)) {
            return "No tienes acceso a esta nota";
        }
        else {
            noteRepository.delete(notes);
            return "Nota eliminada con exito";
        }
    }
}
