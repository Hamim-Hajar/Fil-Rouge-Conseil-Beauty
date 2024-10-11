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
    private AuthenticationManager authenticationManager;

    @Mock
    private PasswordEncoder passwordEncoder;

    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authenticationService = new AuthenticationService(userRepository, authenticationManager, passwordEncoder);
    }

    @Test
    void testSignup_VisiteurRole() {
        RegisterUserDto input = new RegisterUserDto();
        input.setUserName("testUser");
        input.setEmail("test@example.com");
        input.setPassword("password123");
        input.setRole(UserRole.VISITEUR);

        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));
        User result = authenticationService.signup(input);

        // Vérifications
        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
        assertEquals("test@example.com", result.getEmail());
        assertEquals("encodedPassword", result.getPassword());
        assertEquals(UserRole.VISITEUR, result.getRole());
        assertTrue(result instanceof Visiteur);

        verify(passwordEncoder).encode("password123");
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testAuthenticate_SuccessfulLogin() {
        LoginUserDto input = new LoginUserDto();
        input.setUserName("testUser");
        input.setPassword("password123");

        Authentication mockAuthentication = mock(Authentication.class);
        User mockUser = new Visiteur();
        mockUser.setUsername("testUser");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(mockAuthentication);
        when(userRepository.findByUsername("testUser")).thenReturn(mockUser);

        User result = authenticationService.authenticate(input);
        assertNotNull(result);
        assertEquals("testUser", result.getUsername());

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userRepository).findByUsername("testUser");
    }

    @Test
    void testAuthenticate_InvalidCredentials() {
        // Préparation des données de test
        LoginUserDto input = new LoginUserDto();
        input.setUserName("testUser");
        input.setPassword("wrongPassword");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Invalid username or password"));

        // Exécution du test et vérification
        assertThrows(BadCredentialsException.class, () -> authenticationService.authenticate(input));

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }
}