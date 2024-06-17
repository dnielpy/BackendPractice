package com.example.demo.repositories;

import com.example.demo.entitys.Notes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesRepository extends JpaRepository<Notes, Long> {
    Notes findByTittle(String tittle);
}
