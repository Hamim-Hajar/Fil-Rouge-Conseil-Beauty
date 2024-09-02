package com.example.conseil.configs;

import com.example.conseil.repository.UserRepository;
import com.example.conseil.entities.Admin;
import com.example.conseil.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.existsByEmail("admin@example.com")) {
            Admin admin = new Admin();
            admin.setEmail("admin@example.com");
            admin.setFullName("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRole(UserRole.ADMIN);
            admin.setUsername("admin");
            userRepository.save(admin);
        }
    }

}
