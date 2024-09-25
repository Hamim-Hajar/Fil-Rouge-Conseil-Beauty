package com.example.conseil.dto;

import com.example.conseil.enums.RecipeCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDto {
    private Long id;
    private String name;
    private String description;
    private String ingredients;
    private LocalDateTime datePublication;
    private Long specialistId;
    private List<Long> commentIds;
    private List<Long> favoriteRecipeIds;
    private RecipeCategory category;
}

