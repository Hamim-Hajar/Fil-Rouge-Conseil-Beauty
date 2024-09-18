package com.example.conseil.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    private Long id;
    private String titre;
    private String contenu;
    private Date datePublication;
    private Long specialistId; // Pour représenter la relation avec Specialist
}

