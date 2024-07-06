package com.example.demo.Auth;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<AuthEntity, Long> {
    AuthEntity findByUserName(String username);
}
