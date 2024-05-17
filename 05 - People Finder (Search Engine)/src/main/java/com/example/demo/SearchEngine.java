package com.example.demo;

import java.util.*;

public class SearchEngine {
    private String query;
    private List<String> query_words;
    private List<String> query_information = new ArrayList<>();
    private List<String> important_words = new ArrayList<>();
    private List<PeopleDatabase> all_people_data = new ArrayList<>();
    private List<PeopleDatabase> filtred_people_data = new ArrayList<>();
    private PeopleRepository peopleRepository;

    //Constructor
    public SearchEngine(String Query){
        this.query = Query;
        this.query_words = List.of(this.query.split(" "));
    }

    //Text Treatment
    private void queryTreatment(){
        this.query = this.query.toLowerCase(Locale.ROOT);
    }

    //Determine if the word is a key word
    public boolean itsAKey(String x){
        if (important_words.contains(x)) {
            return true;
        }
        else {
            return false;
        }
    }

    //get important data
    public void filter(){
        for (int i = 0; i < this.query_words.size(); i++) {
            String posible_palabra_clave = this.query_words.get(i);
            String posible_data = "";
            if (i+1 < this.query_words.size()) {
                posible_data = this.query_words.get(i+1);
            }
            if (itsAKey(posible_palabra_clave)) {
                if (!itsAKey(posible_data) && posible_data != "") {
                    this.query_information.add(posible_data);
                }
            }
        }
    }

    public List<String> getQuery_information() {
        return query_information;
    }

    public void setImportant_words(String important_words) {
        this.important_words.add(important_words);
    }

    public void filter_people(){
        //Usando las palabras claves, sacar las personas que coinciden
        //Sacar los datos de la bd
        List<PeopleDatabase> persons_data = peopleRepository.findAll();

        //Recorrer sus propieddes y si son iguales a lo que me pasaron por parametro, añadirlos a filtredp_people_data

    }
}
