package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        ListService listService = new ListService(username, tittle);
        return listService.createList();
    }
    @GetMapping()
    public String getList(@RequestParam String tittle){
        ListService listService = new ListService(tittle);
        return listService.getList();
    }
    @PutMapping()
    public String updateList(@RequestParam String username, @RequestParam String tittle){
        ListService listService = new ListService(username, tittle);
        return listService.updateList();
    }
    @DeleteMapping()
    public String deleteList(@RequestParam String tittle){
        ListService listService = new ListService(tittle);
        return listService.deleteList();
    }
    @PutMapping("/addList")
    public String addList( @RequestParam String username, @RequestParam String list_tittle, @RequestParam String note_tittle) {
    ListService listService = new ListService(username, list_tittle, note_tittle);
    return listService.addList();
    }
}

