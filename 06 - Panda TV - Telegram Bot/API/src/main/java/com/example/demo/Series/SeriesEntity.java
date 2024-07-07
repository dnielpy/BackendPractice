package com.example.demo.Series;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jdk.jfr.Enabled;
import jakarta.persistence.*;

@Entity
@Table(name = "series")
public class SeriesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String url;

    public SeriesEntity() {
    }

    public SeriesEntity(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
