package com.example.demo;

import java.util.*;

public class SearchEngine {
    private String query;
    private List<String> query_words;
    private List<String> query_information = new ArrayList<>();
    private List<String> important_words = new ArrayList<>();
    private List<Person> sospechosos = new ArrayList<>();
    private List<Person> persons_data;

    //Constructor
    public SearchEngine(String Query, List<Person>PersonData){
        this.query = Query;
        this.query_words = List.of(this.query.split(" "));
        this.persons_data = PersonData;
    }

    //Text Treatment
    private void queryTreatment(){
        this.query = this.query.toLowerCase(Locale.ROOT);
    }

    //Determine if the word is a key word
    private boolean itsAKey(String x){
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

    public void filter_people(){
        //Usando las palabras claves, sacar las personas que coinciden


        //Recorrer sus propieddes y si son iguales a lo que me pasaron por parametro, añadirlos a filtredp_people_data
        for (int i = 0; i < persons_data.size(); i++) {
            if (
                    query_information.contains(persons_data.get(i).getName().toLowerCase())
                    ^ query_information.contains(persons_data.get(i).getAge().toLowerCase())
                    ^ query_information.contains(persons_data.get(i).getCountry().toLowerCase())
                    ^ query_information.contains(persons_data.get(i).getColor().toLowerCase())
                    ^ query_information.contains(persons_data.get(i).getGender().toLowerCase())
                    ^ query_information.contains(persons_data.get(i).getId().toLowerCase())
                    ^ query_information.contains(persons_data.get(i).getPhone().toLowerCase())
            ) {
                sospechosos.add(persons_data.get(i));
            }
        }
    }

    public List<Person> getSospechosos() {
        return sospechosos;
    }

    //Crear una lista que contenga todas las palabras claves
    public void setKeysWords(){
        List<String> keysWords = new ArrayList<>(Arrays.asList(
                "hombre", "mujer", "negro", "blanco", "cuba", "españa",
                "francia", "italia", "alemania", "rusia", "japon", "china",
                "india", "australia", "canada", "mexico", "brasil", "argentina",
                "chile", "peru", "colombia", "venezuela", "ecuador", "bolivia",
                "paraguay", "uruguay", "portugal", "inglaterra", "escocia", "irlanda"
        ));

        for (int i = 1; i <= 90; i++) {
            keysWords.add(String.valueOf(i));
        }

    }


    public void setImportant_words(String important_words) {
        this.important_words.add(important_words);
    }
}
