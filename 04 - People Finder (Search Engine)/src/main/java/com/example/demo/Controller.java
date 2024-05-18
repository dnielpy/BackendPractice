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

    @GetMapping("/")
    public List<Person> getStudent() {

        //Change this for http request
        String name = "Daniel";
        int age = 13;
        String country = "Cuba";
        String color = "White";
        String gender = "Black";
        String phone = "5531122";

        List<String> query = new ArrayList<>();
        query.add(name);
        query.add(String.valueOf(age));
        query.add(country);
        query.add(color);
        query.add(gender);
        query.add(phone);

        data = peopleRepository.findAll();
        searchengine = new SearchEngine(query, data);

        //return searchengine.StartEngine();
    }
}