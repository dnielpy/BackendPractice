package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    @Autowired
    private PeopleRepository peopleRepository;

    private List<Person> data;
    private SearchEngine searchengine;

    @PostMapping("/person")
    public List<Person> addStudent(
            @RequestParam String postName,
            @RequestParam String postAge,
            @RequestParam String postCountry,
            @RequestParam String postColor,
            @RequestParam String postGender,
            @RequestParam String postPhone
    ) {
        List<String> query = new ArrayList<>();
        query.add(postName);
        query.add(postAge);
        query.add(postCountry);
        query.add(postColor);
        query.add(postGender);
        query.add(postPhone);

        data = peopleRepository.findAll();
        searchengine = new SearchEngine(query, data);

        return searchengine.filter();
    }
}