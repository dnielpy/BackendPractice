package com.example.demo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    SearchEngine searchengine = new SearchEngine("hombre blanco edad 35");

    @GetMapping("/")
    public List<String> getStudent() {
        searchengine.setImportant_words("edad");
        searchengine.filter();
        return searchengine.getQuery_information();
    }
}
