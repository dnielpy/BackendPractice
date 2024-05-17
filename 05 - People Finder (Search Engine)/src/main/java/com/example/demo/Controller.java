package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    private PeopleRepository peopleRepository;

    List<PeopleDatabase> data = peopleRepository.findAll();

    SearchEngine searchengine = new SearchEngine("hombre blanco edad 35", data);

    @GetMapping("/")
    public List<PeopleDatabase> getStudent() {
        searchengine.setImportant_words("edad");
        searchengine.filter();
        searchengine.filter_people();
        return searchengine.getSospechosos();
    }
}
