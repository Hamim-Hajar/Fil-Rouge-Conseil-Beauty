package com.example.conseil.services;

import com.example.conseil.dto.LoginUserDto;
import com.example.conseil.dto.RegisterUserDto;
import com.example.conseil.entities.Admin;
import com.example.conseil.entities.Specialist;
import com.example.conseil.entities.User;
import com.example.conseil.entities.Visiteur;
import com.example.conseil.enums.UserRole;
import com.example.conseil.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {
        User user;

        switch (input.getRole()) {
            case UserRole.VISITEUR:
                user = new Visiteur();
                break;
            case UserRole.SPECIALIST:
                user = new Specialist();
                break;
            case UserRole.ADMIN:
                user = new Admin();
                break;
            default:
                throw new IllegalArgumentException("Invalid user role");
        }

        user.setUsername(input.getUserName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setRole(input.getRole());

        return userRepository.save(user);
    }
    public User authenticate(LoginUserDto input) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            input.getUserName(),
                            input.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            User user = userRepository.findByUsername(input.getUserName());
            if (user == null) {
                throw new UsernameNotFoundException("User not found with username: " + input.getUserName());
            }
            return user;
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid username or password");
        } catch (Exception e) {
            throw new RuntimeException("Authentication failed: " + e.getMessage(), e);
        }
    }
}
