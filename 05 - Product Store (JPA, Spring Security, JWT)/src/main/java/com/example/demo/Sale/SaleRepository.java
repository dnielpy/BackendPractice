package com.example.demo.Sale;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<SaleEntity, Long> {
    SaleEntity findByEmail(String email);
}
