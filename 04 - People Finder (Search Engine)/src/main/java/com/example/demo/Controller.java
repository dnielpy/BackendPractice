package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    private PeopleRepository peopleRepository;

    private List<Person> data;
    private SearchEngine searchengine;

    public void init() {
        if (data == null || searchengine == null) {
            data = peopleRepository.findAll();
            List<String> query = List.of("Daniel", "", "", "", "", "");
            searchengine = new SearchEngine(query, data);
        }
    }

    @GetMapping("/")
    public List<Person> getStudent() {
        init();
        return searchengine.StartEngine();
    }
}