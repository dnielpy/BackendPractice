package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Locale;

public class ListService {

    private String username;
    private String tittle;
    private String note;
    private UserRepository userRepository;
    private NotesRepository noteRepository;
    private ListRepository listRepository;

    public ListService(String username, String tittle, String note, UserRepository userRepository, NotesRepository noteRepository, ListRepository listRepository) {
        this.username = username.toLowerCase(Locale.ROOT);
        this.tittle = tittle.toLowerCase(Locale.ROOT);
        this.note = note;
        this.userRepository = userRepository;
        this.noteRepository = noteRepository;
        this.listRepository = listRepository;
    }

    public ListService(String username, String tittle, UserRepository userRepository, NotesRepository noteRepository, ListRepository listRepository) {
        this.username = username.toLowerCase(Locale.ROOT);
        this.tittle = tittle.toLowerCase(Locale.ROOT);
        this.userRepository = userRepository;
        this.noteRepository = noteRepository;
        this.listRepository = listRepository;
    }

    public ListService(String username, UserRepository userRepository, NotesRepository noteRepository, ListRepository listRepository) {
        this.username = username.toLowerCase(Locale.ROOT);
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
        else {
            List<Notes> notes_list = lists.getNotes();
            notes_list.add(notes);
            lists.setNotes(notes_list);
            listRepository.save(lists);
            return "Nota agregada a la lista con exito";
        }
    }
}
