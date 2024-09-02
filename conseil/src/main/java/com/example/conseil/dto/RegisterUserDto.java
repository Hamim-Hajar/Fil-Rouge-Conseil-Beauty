package com.example.conseil.dto;

import com.example.conseil.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDto {
    private String userName;

    private String email;

    private String password;
    @Getter
    private UserRole role;
}
