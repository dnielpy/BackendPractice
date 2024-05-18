package com.example.demo;

import java.util.*;

public class SearchEngine {
    private List<String> query;
    private List<Person> data;

    //Query Data
    private String queryName = this.query.get(0);
    private String queryAge = this.query.get(1);
    private String queryCountry = this.query.get(2);
    private String queryColor = this.query.get(3);
    private String queryGender = this.query.get(4);
    private String queryPhone = this.query.get(5);

    public SearchEngine(List<String> query, List<Person> data) {
        this.query = query;
        this.data = data;
    }

    //Lower Key all query data
    private void lowerData(){
        for (int i = 0; i < query.size(); i++) {
            query.get(i).toLowerCase();
        }
    }

    


}
