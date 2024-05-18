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

        //System.out.println(searchengine.filter().get(0).getName());
        return searchengine.filter();
    }

//
//    @GetMapping("/")
//    public List<Person> getStudent() {
//        //Change this for http request
//        String name = "vallie";
//        int age = 0;
//        String country = "";
//        String color = "";
//        String gender = "";
//        String phone = "";
//
//        List<String> query = new ArrayList<>();
//        query.add(name);
//        if (age == 0) {
//            query.add("");
//        }
//        else{
//            query.add(String.valueOf(age));
//        }
//        query.add(country);
//        query.add(color);
//        query.add(gender);
//        query.add(phone);
//
//        data = peopleRepository.findAll();
//        searchengine = new SearchEngine(query, data);
//
//        //System.out.println(searchengine.filter().get(0).getName());
//        return searchengine.filter();
//    }
}