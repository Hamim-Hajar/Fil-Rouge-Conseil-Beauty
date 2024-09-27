package com.example.conseil.dto;

import com.example.conseil.enums.RecipeCategory;
import jakarta.persistence.Transient;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeDto {
    private Long id;
    private String name;
    private String description;
    private String ingredients;
    private Date datePublication;
    private String instructions;
    private Long specialistId;
    @Transient
    private byte[] image;
    private List<Long> commentIds;
    private List<Long> favoriteRecipeIds;
    private RecipeCategory category;
}

