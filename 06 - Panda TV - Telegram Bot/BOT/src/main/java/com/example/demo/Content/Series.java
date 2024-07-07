package com.example.demo.Content;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Series {

    public List<String> getNetflix() {
        String url = "https://visuales.uclv.cu/Series/NETFLIX/";
        List<String> seriesList = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(url).get();
            Elements series = doc.select("td a");
            for (Element serie : series) {
                String serieName = serie.text();
                if (!serieName.equals("Parent Directory")) {
                    // Remove the trailing slash
                    if (serieName.endsWith("/")) {
                        serieName = serieName.substring(0, serieName.length() - 1);
                    }
                    seriesList.add(serieName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return seriesList;
    }

    public List<String> getSeries() {
        List<String> seriesList = new ArrayList<>();
        try {
            // Parse the HTML file
            Document doc = Jsoup.parse(new File("API/src/main/java/com/example/demo/Series/ingles.txt"), "UTF-8");
            Elements series = doc.select("td a");
            for (Element serie : series) {
                String serieName = serie.text();
                if (!serieName.equals("Parent Directory")) {
                    // Remove the trailing slash
                    if (serieName.endsWith("/")) {
                        serieName = serieName.substring(0, serieName.length() - 1);
                    }
                    seriesList.add(serieName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return seriesList;
    }
}
