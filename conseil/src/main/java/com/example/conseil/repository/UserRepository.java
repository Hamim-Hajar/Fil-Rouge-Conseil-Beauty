package com.example.conseil.repository;

import com.example.conseil.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String userName);
    boolean existsByEmail(String email);

}
