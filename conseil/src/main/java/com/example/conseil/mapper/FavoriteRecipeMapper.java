package com.example.conseil.mapper;

import com.example.conseil.dto.FavoriteRecipeDto;
import com.example.conseil.entities.FavoriteRecipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FavoriteRecipeMapper {
    @Mapping(source = "recipe.id", target = "recipeId")
    @Mapping(source = "visiteur.id", target = "visiteurId")
    FavoriteRecipeDto toDto(FavoriteRecipe favoriteRecipe);

    @Mapping(source = "recipeId", target = "recipe.id")
    @Mapping(source = "visiteurId", target = "visiteur.id")
    FavoriteRecipe toEntity(FavoriteRecipeDto favoriteRecipeDto);
}