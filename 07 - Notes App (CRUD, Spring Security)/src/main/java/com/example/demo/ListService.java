package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class ListService {

    private String username;
    private String tittle;
    private String note;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotesRepository noteRepository;
    @Autowired
    private ListRepository listRepository;

    public ListService(String username, String tittle, String note) {
        this.username = username;
        this.tittle = tittle;
        this.note = note;
    }

    public ListService(String username, String tittle){
        this.username = username;
        this.tittle = tittle;
    }

    public ListService(String username) {
        this.username = username;
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
