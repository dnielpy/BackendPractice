package com.example.demo.repositories;

import com.example.demo.entitys.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Long, ProductEntity> {
}
