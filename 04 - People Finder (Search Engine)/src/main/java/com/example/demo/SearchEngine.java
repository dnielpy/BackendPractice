package com.example.demo;

import java.util.*;

public class SearchEngine {
    private List<String> query;
    private List<Person> data;

    public SearchEngine(List<String> query, List<Person> data) {
        this.query = query;
        this.data = data;
    }

    //Lower Key all query data
    public void lowerData() {
        for (int i = 0; i < query.size(); i++) {
            query.get(i).toLowerCase(Locale.ROOT);
        }
    }

    //filter
    public List<Person> filter() {
        List<Person> matches = new ArrayList<>();
        int conditions = 0;
        if (query.get(0) != "") {
            conditions++;
        }
        if (query.get(1) != "") {
            conditions++;
        }
        if (query.get(2) != "") {
            conditions++;
        }
        if (query.get(3) != "") {
            conditions++;
        }
        if (query.get(4) != "") {
            conditions++;
        }
        if (query.get(5) != "") {
            conditions++;
        }

        for (int i = 0; i < this.data.size(); i++) {
            int checks = 0;
            if (data.get(i).getName().toLowerCase(Locale.ROOT).equals(this.query.get(0).toLowerCase(Locale.ROOT))) {
                checks++;
            }
            if (data.get(i).getAge().toLowerCase(Locale.ROOT).equals(this.query.get(1).toLowerCase(Locale.ROOT))) {
                checks++;
            }
            if (data.get(i).getCountry().toLowerCase(Locale.ROOT).equals(this.query.get(2).toLowerCase(Locale.ROOT))) {
                checks++;
            }
            if (data.get(i).getColor().toLowerCase(Locale.ROOT).equals(this.query.get(3).toLowerCase(Locale.ROOT))) {
                checks++;
            }
            if (data.get(i).getGender().toLowerCase(Locale.ROOT).equals(this.query.get(4).toLowerCase(Locale.ROOT))) {
                checks++;
            }
            if (data.get(i).getPhone().toLowerCase(Locale.ROOT).equals(this.query.get(5).toLowerCase(Locale.ROOT))) {
                checks++;
            }

            if (checks == conditions) {
                matches.add(data.get(i));
            }
        }
        return matches;
    }
}
