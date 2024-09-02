package com.example.conseil.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EmailRequest {
    private String to;
    private String subject;
    private String body;
}
