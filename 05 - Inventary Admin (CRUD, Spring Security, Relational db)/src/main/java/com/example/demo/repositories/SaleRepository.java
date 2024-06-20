package com.example.demo.repositories;

import com.example.demo.entitys.SaleEntity;
import com.example.demo.entitys.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<SaleEntity, Long> {
    SaleEntity findByEmail(String name);
}
