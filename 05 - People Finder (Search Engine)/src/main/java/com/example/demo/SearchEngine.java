package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class SearchEngine {
    private String query;
    private List<String> query_important_words = new ArrayList<>();
    private List<String> important_words = new ArrayList<>();
    private List<PeopleDatabase> people_data = new ArrayList<>();

    //Constructor
    public SearchEngine(String Query){
        this.query = Query;

        //Agregar pa quitar los puntos etc...
    }

    //Text Treatment
    public void queryTreatment(){
        this.query = this.query.toLowerCase(Locale.ROOT);
    }

    //Add the important words to a list
    public void filter(){
        for (int i = 0; i < important_words.size(); i++) {
            String temp_word = important_words.get(i);
            if (this.query.contains(temp_word)) {
                this.query_important_words.add(temp_word);
            }
        }
    }

    //Sacar los datos de la bd
    private PeopleRepository peopleRepository;
    List<PeopleDatabase> persons_data = peopleRepository.findAll();
}
