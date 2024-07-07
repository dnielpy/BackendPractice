package com.example.demo.User;


import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
    UserEntity findByUserid(String userid);
}
