package com.example.demo.Series;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SeriesController {

    @Autowired
    SeriesService seriesService;

    @PostMapping
    public ResponseEntity<SeriesEntity> createSeries(@RequestParam String name, @RequestParam String url, @RequestParam String lan) {
        try {
            SeriesEntity series = seriesService.createSeries(name, url, lan);
            return new ResponseEntity<>(series, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<SeriesEntity> getSeries(@RequestParam String name) {
        try {
            SeriesEntity series = seriesService.getSeries(name);
            return new ResponseEntity<>(series, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all/en")
    public ResponseEntity<List<SeriesEntity>> getAllSeriesEn() {
        try {
            List<SeriesEntity> series = seriesService.getSeriesFromTxt();
            return new ResponseEntity<>(series, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all/esp")
    public ResponseEntity<List<SeriesEntity>> getAllSeriesEsp() {
        try {
            List<SeriesEntity> series = seriesService.getAllSeriesEsp();
            return new ResponseEntity<>(series, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<SeriesEntity> updateSeries(@RequestParam String name, @RequestParam String newName) {
        try {
            SeriesEntity series = seriesService.updateSeries(name, newName);
            return new ResponseEntity<>(series, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteSeries(@RequestParam String name) {
        try {
            seriesService.deleteSeries(name);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping("/update")
//    public ResponseEntity<Void> update(){
//        try {
//            seriesService.updateContent();
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (IllegalArgumentException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
}
