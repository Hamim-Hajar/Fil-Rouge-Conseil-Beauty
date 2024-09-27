//package com.example.conseil.services;
//
//import com.example.conseil.dto.LoginUserDto;
//import com.example.conseil.dto.RegisterUserDto;
//import com.example.conseil.entities.User;
//import com.example.conseil.entities.Visiteur;
//import com.example.conseil.enums.UserRole;
//import com.example.conseil.exception.ResourceNotFoundException;
//import com.example.conseil.repository.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import static org.mockito.ArgumentMatchers.any;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//import static org.mockito.Mockito.*;
//
//
//
//class AuthenticationServiceTest {
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private PasswordEncoder passwordEncoder;
//
//    @Mock
//    private AuthenticationManager authenticationManager;
//
//    @InjectMocks
//    private AuthenticationService authenticationService;
//
//    private RegisterUserDto registerUserDto;
//    private LoginUserDto loginUserDto;
//    private User user;
//
//    @BeforeEach
//    void setUp() {
//        registerUserDto = new RegisterUserDto();
//        registerUserDto.setUserName("testUser");
//        registerUserDto.setEmail("test@example.com");
//        registerUserDto.setPassword("password");
//        registerUserDto.setRole(UserRole.VISITEUR);
//
//        loginUserDto = new LoginUserDto();
//        loginUserDto.setUserName("testUser");
//        loginUserDto.setPassword("password");
//
//        user = new Visiteur(); // Vous pouvez utiliser Admin ou Specialist aussi
//        user.setUsername(registerUserDto.getUserName());
//        user.setEmail(registerUserDto.getEmail());
//        user.setPassword("encodedPassword");
//        user.setRole(registerUserDto.getRole());
//    }
//    @Test
//    void signup() {
//        when(passwordEncoder.encode(registerUserDto.getPassword())).thenReturn("encodedPassword");
//        when(userRepository.save(any(User.class))).thenReturn(user);
//
//        User createdUser = authenticationService.signup(registerUserDto);
//
//        assertNotNull(createdUser);
//        assertEquals("testUser", createdUser.getUsername());
//        verify(passwordEncoder).encode(registerUserDto.getPassword());
//        verify(userRepository).save(any(User.class));
//
//
//    }
//
//    @Test
//    void authenticate() {
//        Authentication authentication = mock(Authentication.class);
//        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
//        when(userRepository.findByUsername(loginUserDto.getUserName())).thenReturn(user);
//
//        User authenticatedUser = authenticationService.authenticate(loginUserDto);
//
//        assertNotNull(authenticatedUser);
//        assertEquals("testUser", authenticatedUser.getUsername());
//        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
//        verify(userRepository).findByUsername(loginUserDto.getUserName());
//        assertEquals(authenticatedUser.getUsername(), SecurityContextHolder.getContext().getAuthentication().getName());
//    }
//    @Test
//    void testAuthenticateUserNotFound() {
//        Authentication authentication = mock(Authentication.class);
//        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
//        when(userRepository.findByUsername(loginUserDto.getUserName())).thenReturn(null);
//
//        assertThrows(ResourceNotFoundException.class, () -> {
//            authenticationService.authenticate(loginUserDto);
//        });
//    }
//}