package com.example.demo.controllers;

import com.example.demo.repositories.ListRepository;
import com.example.demo.services.ListService;
import com.example.demo.repositories.NotesRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/list")
public class ListController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotesRepository noteRepository;
    @Autowired
    private ListRepository listRepository;

    //List CRUD
    @PostMapping()
    public String createList(@RequestParam String username, @RequestParam String tittle){
        ListService listService = new ListService(username, tittle, userRepository, noteRepository, listRepository);
        return listService.createList();
    }
    @GetMapping()
    public String getList(@RequestParam String tittle, Principal principal){
        ListService listService = new ListService(tittle,  principal.getName(), userRepository, noteRepository, listRepository);
        return listService.getList();
    }
    @PutMapping()
    public String updateList(@RequestParam String username, @RequestParam String tittle, Principal principal){
        ListService listService = new ListService(username, tittle, principal.getName(), userRepository, noteRepository, listRepository);
        return listService.updateList();
    }
    @DeleteMapping()
    public String deleteList(@RequestParam String tittle, Principal principal){
        ListService listService = new ListService(tittle, principal.getName(), userRepository, noteRepository, listRepository);
        return listService.deleteList();
    }
    @PutMapping("/addList")
    public String addList( @RequestParam String username, @RequestParam String list_tittle, @RequestParam String note_tittle, Principal principal) {
    ListService listService = new ListService(username, list_tittle, note_tittle,  principal.getName(), userRepository, noteRepository, listRepository);
    return listService.addList();
    }
}

