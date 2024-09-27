package com.example.conseil.mapper;


import com.example.conseil.dto.RecipeDto;

import com.example.conseil.entities.Recipe;
import org.mapstruct.Mapper;

import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RecipeMapper {

   RecipeMapper MAPPER =Mappers.getMapper(RecipeMapper.class);
RecipeDto recipeToRecipeDto(Recipe recipe);
Recipe recipeDtoToRecipe(RecipeDto recipeDto);



}