package com.example.conseil.controllers;

import com.example.conseil.dto.LoginUserDto;
import com.example.conseil.dto.RegisterUserDto;
import com.example.conseil.entities.LoginResponse;
import com.example.conseil.entities.User;
import com.example.conseil.enums.UserRole;
import com.example.conseil.services.AuthenticationService;
import com.example.conseil.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {

        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) throws Exception {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        UserRole userrole = authenticatedUser.getRole();
        Long user = authenticatedUser.getId();

        System.out.println(userrole);
        String jwtToken = jwtService.generateToken(authenticatedUser, userrole,user);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
    @GetMapping("/status")
    public ResponseEntity<Boolean> isAuthenticated(Principal principal) {
        return ResponseEntity.ok(principal != null);
    }
}
