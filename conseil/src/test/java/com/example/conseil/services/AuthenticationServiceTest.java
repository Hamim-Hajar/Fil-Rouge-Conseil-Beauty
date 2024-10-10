package com.example.conseil.services;

import com.example.conseil.dto.LoginUserDto;
import com.example.conseil.dto.RegisterUserDto;
import com.example.conseil.entities.User;
import com.example.conseil.entities.Visiteur;
import com.example.conseil.enums.UserRole;
import com.example.conseil.exception.ResourceNotFoundException;
import com.example.conseil.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

class AuthenticationServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthenticationService authenticationService;
    @Mock
    private AuthenticationManager authenticationManager;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSignup_Visiteur() {
        RegisterUserDto input = new RegisterUserDto();
        input.setUserName("testUser");
        input.setEmail("test@example.com");
        input.setPassword("password123");
        input.setRole(UserRole.VISITEUR);
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");
        Visiteur visiteur = new Visiteur();
        visiteur.setUsername("testUser");
        visiteur.setEmail("test@example.com");
        visiteur.setPassword("encodedPassword");
        visiteur.setRole(UserRole.VISITEUR);
        when(userRepository.save(any(Visiteur.class))).thenReturn(visiteur);
        User result = authenticationService.signup(input);
        assertEquals("testUser", result.getUsername());
        assertEquals("test@example.com", result.getEmail());
        assertEquals("encodedPassword", result.getPassword());
        assertEquals(UserRole.VISITEUR, result.getRole());
        verify(passwordEncoder).encode("password123");
        verify(userRepository).save(any(Visiteur.class));
    }
    @Test
    public void testAuthenticate_Success() {
        LoginUserDto input = new LoginUserDto();
        input.setUserName("testUser");
        input.setPassword("password123");
        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        Visiteur visiteur = new Visiteur();
        visiteur.setUsername("testUser");
        when(userRepository.findByUsername("testUser")).thenReturn(visiteur);
        User result = authenticationService.authenticate(input);
        assertEquals("testUser", result.getUsername());
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userRepository).findByUsername("testUser");
    }

    @Test
    public void testAuthenticate_BadCredentials() {
        LoginUserDto input = new LoginUserDto();
        input.setUserName("testUser");
        input.setPassword("wrongPassword");
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Invalid username or password"));
        BadCredentialsException exception = assertThrows(BadCredentialsException.class, () -> {
            authenticationService.authenticate(input);
        });
        assertEquals("Invalid username or password", exception.getMessage());
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }
}