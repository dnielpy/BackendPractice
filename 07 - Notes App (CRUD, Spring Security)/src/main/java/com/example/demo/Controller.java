package com.example.demo;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

//    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotesRepository noteRepository;
    @Autowired ListRepository listRepository;

    //Users CRUD
    @PostMapping("/createUser")
    public String createUser(@RequestParam String username, @RequestParam String password){
        Users user = new Users(username, password);
        if (userRepository.findByUserName(username) == null) {
            userRepository.save(user);
            return "Usuario guardado con exito";
        }
        else {
            return "Nombre de Usuario ya existe en la base de datos";
        }
    }
    @GetMapping("/getUser")
    public String getUser(@RequestParam String username){
        Users user = userRepository.findByUserName(username);
        if (user == null) {
            return "El usuario no existe";
        }
        else {
            //Rellenar esto
            String user_info = "Name: \n " + user.getUsername() + "\nNotes: ";
            return user_info;
        }
    }
    @PutMapping("/updateUser")
    public String updateUser(@RequestParam String username, @RequestParam String password){
        Users user = userRepository.findByUserName(username);
        if (user == null) {
            return "El usuario no existe";
        }
        else {
            user.setUsername(username);
            user.setPassword(password);
            userRepository.save(user);
            return "Usuario actualizado con exito";
        }
    }
    @PutMapping("/deleteUser")
    public String deleteUser(@RequestParam String username, @RequestParam String password){
        Users user = userRepository.findByUserName(username);
        if (user == null) {
            return "El usuario no existe";
        }
        else {
            if (user.getPassword().equals(password)) {
                userRepository.delete(user);
                return "Usuario eliminado con exito";
            }
            else {
                return "Contrasenna incorrecta";
            }
        }
    }

    //Notes CRUD
    @PostMapping("/createNote")
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
    @GetMapping("/getNote")
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
    @PutMapping("/updateNote")
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
    @PutMapping("/deleteNote")
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

    //List CRUD
    @PostMapping("/createList")
    public String createList(@RequestParam String username, @RequestParam String lists_tittle){
        Lists lists = new Lists(username, lists_tittle, null);
        if (listRepository.findByTittle(lists_tittle) == null) {
            listRepository.save(lists);
            return "Lista guardada con exito";
        }
        else {
            return "Titulo de Lista ya existe en la base de datos";
        }
    }
    @GetMapping("/getList")
    public String getList(@RequestParam String tittle){
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
    

}
