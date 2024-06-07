package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ListRepository extends JpaRepository<List, String> {
}
