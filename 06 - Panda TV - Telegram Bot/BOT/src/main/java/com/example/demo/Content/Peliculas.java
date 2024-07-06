package com.example.demo.Content;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Peliculas {

    public List<String> getPeliculas() {
        String url = "https://visuales.uclv.cu/Peliculas/";
        List<String> peliculasList = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(url).get();
            Elements peliculas = doc.select("td a");
            for (Element pelicula : peliculas) {
                String peliculaName = pelicula.text();
                if (!peliculaName.equals("Parent Directory")) {
                    // Remove the trailing slash
                    if (peliculaName.endsWith("/")) {
                        peliculaName = peliculaName.substring(0, peliculaName.length() - 1);
                    }
                    peliculasList.add(peliculaName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return peliculasList;
    }
}
