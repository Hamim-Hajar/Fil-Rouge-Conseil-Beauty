package com.example.conseil.dto;


import jakarta.persistence.Transient;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleDto {
    private Long id;
    private String titre;
    private String contenu;
    private Date datePublication;
    private Long specialist_id;
    private Long comment_id;
    @Transient
    private byte[] image;
}

