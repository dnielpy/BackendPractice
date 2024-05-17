package com.example.demo;

import java.util.*;

public class SearchEngine {
    private List<String> query;
    private List<Person> sospechosos = new ArrayList<>();
    private List<Person> persons_data;

    //Constructor
    public SearchEngine(List<String> Query, List<Person>PersonData){
        this.query = Query;
        this.persons_data = PersonData;
    }

    //Text Treatment
    private void queryTreatment(){
        for (int i = 0; i < query.size(); i++) {
            query.get(i).toLowerCase();
        }
    }

    public void filter_people(){
        //Usando las palabras claves, sacar las personas que coinciden
        //Recorrer sus propieddes y si son iguales a lo que me pasaron por parametro, añadirlos a filtredp_people_data
        for (int i = 0; i < persons_data.size(); i++) {
            if (
                            persons_data.get(i).getName().toLowerCase().equals((query.get(0))) ||
                            persons_data.get(i).getAge().toLowerCase().equals(query.get(1)) ||
                            persons_data.get(i).getCountry().toLowerCase().equals(query.get(2)) ||
                            persons_data.get(i).getColor().toLowerCase().equals(query.get(3)) ||
                            persons_data.get(i).getGender().toLowerCase().equals(query.get(4)) ||
                            persons_data.get(i).getPhone().toLowerCase().equals(query.get(5))
            ) {
                sospechosos.add(persons_data.get(i));
            }
        }
    }

    //StartEngine
    public List<Person> StartEngine(){
        queryTreatment();
        filter_people();
        return sospechosos;
    }
}
