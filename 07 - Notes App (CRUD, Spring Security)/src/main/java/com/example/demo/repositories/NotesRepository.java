package com.example.demo.repositories;

import com.example.demo.Notes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesRepository extends JpaRepository<Notes, Long> {
    Notes findByTittle(String tittle);
}
