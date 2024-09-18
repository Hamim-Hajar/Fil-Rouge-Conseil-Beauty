package com.example.conseil.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteRecipeDto {
    private Long id;
    private String recipeName;
    private int value;
    private Long recipeId;
    private Long visiteurId;
}

