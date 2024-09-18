package com.example.conseil.mapper;

import com.example.conseil.dto.RecipeDto;
import com.example.conseil.entities.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecipeMapper {

    // Map Recipe to RecipeDto
    @Mapping(source = "specialist.id", target = "specialistId")
    RecipeDto toDto(Recipe recipe);

    // Map RecipeDto to Recipe
    @Mapping(source = "specialistId", target = "specialist.id")
    Recipe toEntity(RecipeDto recipeDto);
}