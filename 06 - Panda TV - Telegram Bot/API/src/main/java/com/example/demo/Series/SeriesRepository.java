package com.example.demo.Series;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesRepository extends JpaRepository<SeriesEntity, Long> {
    SeriesEntity findByName(String name);
}
