package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchEngine {
    private String query;
    private List<String> query_important_words = new ArrayList<>();
    private List<String> important_words = new ArrayList<>();

    //Constructor
    public SearchEngine(String Query){
        this.query = Query;
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
}
