package com.example.demo.services;

import com.example.demo.entitys.Lists;
import com.example.demo.entitys.Notes;
import com.example.demo.repositories.ListRepository;
import com.example.demo.repositories.NotesRepository;
import com.example.demo.repositories.UserRepository;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class ListService {

    private String username;
    private String tittle;
    private String note;
    private String logged_username;
    private UserRepository userRepository;
    private NotesRepository noteRepository;
    private ListRepository listRepository;

    public ListService(String username, String tittle, String note, String logged_username, UserRepository userRepository, NotesRepository noteRepository, ListRepository listRepository) {
        this.username = username.toLowerCase(Locale.ROOT);
        this.tittle = tittle.toLowerCase(Locale.ROOT);
        this.note = note;
        this.logged_username = logged_username;
        this.userRepository = userRepository;
        this.noteRepository = noteRepository;
        this.listRepository = listRepository;
    }

    public ListService(String username, String tittle, String logged_username, UserRepository userRepository, NotesRepository noteRepository, ListRepository listRepository) {
        this.username = username.toLowerCase(Locale.ROOT);
        this.tittle = tittle.toLowerCase(Locale.ROOT);
        this.logged_username = logged_username;
        this.userRepository = userRepository;
        this.noteRepository = noteRepository;
        this.listRepository = listRepository;
    }

    public ListService(String username, String logged_username, UserRepository userRepository, NotesRepository noteRepository, ListRepository listRepository) {
        this.username = username.toLowerCase(Locale.ROOT);
        this.logged_username = logged_username;
        this.userRepository = userRepository;
        this.noteRepository = noteRepository;
        this.listRepository = listRepository;
    }

    //List CRUD
    public String createList(){
        Lists lists = new Lists(username, tittle);
        if (listRepository.findByTittle(tittle) == null) {
            listRepository.save(lists);
            return "Lista guardada con exito";
        }
        else {
            return "Titulo de Lista ya existe en la base de datos";
        }
    }
    public String getList(){
        Lists lists = listRepository.findByTittle(tittle);
        if (lists == null) {
            return "La nota no existe";
        }
        if (!Objects.equals(lists.getUsername(), logged_username)) {
            return "No tienes acceso a esta lista";
        }
        else {
            //Rellenar esto
            String note_info = "Tittle: \n " + lists.getTittle() + "\nUser: " + lists.getUsername();
            return note_info;
        }
    }
    public String updateList(){
        Lists lists = listRepository.findByTittle(tittle);
        if (lists == null) {
            return "La lista no existe";
        }
        if (!Objects.equals(lists.getUsername(), logged_username)) {
            return "No tienes acceso a esta lista";
        }
        else {
            lists.setTittle(tittle);
            lists.setUsername(username);
            listRepository.save(lists);
            return "Lista actualizada con exito";
        }
    }

    public String deleteList(){
        Lists lists = listRepository.findByTittle(tittle);
        if (lists == null) {
            return "La lista no existe";
        }
        if (!Objects.equals(lists.getUsername(), logged_username)) {
            return "No tienes acceso a esta lista";
        }
        else {
            listRepository.delete(lists);
            return "Lista eliminada con exito";
        }
    }
    public String addList(){
        Lists lists = listRepository.findByTittle(tittle);
        Notes notes = noteRepository.findByTittle(note);
        if (lists == null) {
            return "La lista no existe";
        }
        else if (notes == null) {
            return "La nota no existe";
        }
        if (!Objects.equals(lists.getUsername(), logged_username) || !Objects.equals(notes.getUsername(), logged_username)) {
            return "No tienes acceso a esto";
        }
        else {
            List<Notes> notes_list = lists.getNotes();
            notes_list.add(notes);
            lists.setNotes(notes_list);
            listRepository.save(lists);
            return "Nota agregada a la lista con exito";
        }
    }
}