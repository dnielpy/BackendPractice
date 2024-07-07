package com.example.demo.Series;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

@Service
public class SeriesService {

    @Autowired
    SeriesRepository seriesRepository;

    //Crud operations
    public SeriesEntity createSeries(String name) {
        SeriesEntity series = seriesRepository.findByName(name);
        seriesRepository.save(series);
        return series;
    }

    public SeriesEntity getSeries(String name) {
        return seriesRepository.findByName(name);
    }

    public void deleteSeries(String name) {
        SeriesEntity series = seriesRepository.findByName(name);
        seriesRepository.delete(series);
    }

    public SeriesEntity updateSeries(String name, String newName) {
        SeriesEntity series = seriesRepository.findByName(name);
        series.setName(newName);
        seriesRepository.save(series);
        return series;
    }
}
