package com.example.demo.repositories;

import com.example.demo.entitys.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Long, AdminEntity> {
}
