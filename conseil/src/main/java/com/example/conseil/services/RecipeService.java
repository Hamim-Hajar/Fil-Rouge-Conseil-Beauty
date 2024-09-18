package com.example.conseil.services;

import com.example.conseil.dto.RecipeDto;
import com.example.conseil.entities.Recipe;
import com.example.conseil.enums.RecipeCategory;
import com.example.conseil.exception.RecipeNotFoundException;
import com.example.conseil.mapper.RecipeMapper;
import com.example.conseil.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeMapper recipeMapper;

    public RecipeDto addRecipe(RecipeDto recipeDto) {
        Recipe recipe = recipeMapper.toEntity(recipeDto);
        Recipe savedRecipe = recipeRepository.save(recipe);
        return recipeMapper.toDto(savedRecipe);
    }

    public RecipeDto getRecipeById(Long id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe not found with id: " + id));
        return recipeMapper.toDto(recipe);
    }

    public List<RecipeDto> getAllRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes.stream().map(recipeMapper::toDto).toList();
    }

    public RecipeDto updateRecipe(Long id, RecipeDto updatedRecipeDto) {
        Recipe existingRecipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe not found with id: " + id));

        existingRecipe.setName(updatedRecipeDto.getName());
        existingRecipe.setDescription(updatedRecipeDto.getDescription());
        existingRecipe.setIngredients(updatedRecipeDto.getIngredients());

        Recipe updatedRecipe = recipeRepository.save(existingRecipe);
        return recipeMapper.toDto(updatedRecipe);
    }

    public void deleteRecipe(Long id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe not found with id: " + id));
        recipeRepository.delete(recipe);
    }

    public List<RecipeDto> getRecipesByCategory(RecipeCategory category) {
        List<Recipe> recipes = recipeRepository.findByCategory(category);
        if (recipes.isEmpty()) {
            throw new RecipeNotFoundException("No recipes found for category: " + category);
        }
        return recipes.stream().map(recipeMapper::toDto).toList();
    }
}
