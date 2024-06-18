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
    public String createList(Principal principal, @RequestParam String tittle) {
        ListService listService = new ListService(principal.getName(), tittle, userRepository, noteRepository, listRepository);
        return listService.createList();
    }

    @GetMapping()
    public String getList( Principal principal, @RequestParam String tittle) {
        ListService listService = new ListService(principal.getName(), tittle, userRepository, noteRepository, listRepository);
        return listService.getList();
    }

    @PutMapping()
    public String updateList(@RequestParam String tittle, @RequestParam String new_tittle, Principal principal) {
        ListService listService = new ListService(principal.getName(), tittle, userRepository, noteRepository, listRepository);
        return listService.updateList(new_tittle);
    }

    @DeleteMapping()
    public String deleteList(@RequestParam String tittle, Principal principal) {
        ListService listService = new ListService(principal.getName(), tittle, userRepository, noteRepository, listRepository);
        return listService.deleteList();
    }

    @PutMapping("/addToList")
    public String addToList(@RequestParam String list_tittle, @RequestParam String note_tittle, Principal principal) {
        ListService listService = new ListService(principal.getName(), list_tittle, userRepository, noteRepository, listRepository);
        return listService.addList(note_tittle);
    }
}

