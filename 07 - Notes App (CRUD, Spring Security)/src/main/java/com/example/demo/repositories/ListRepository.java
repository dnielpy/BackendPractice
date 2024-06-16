package com.example.demo.repositories;

import com.example.demo.Lists;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListRepository extends JpaRepository<Lists, String> {
    Lists findByTittle(String tittle);
}
