package com.example.conseil.services;

import com.example.conseil.dto.ArticleDto;
import com.example.conseil.dto.RecipeDto;
import com.example.conseil.entities.Article;
import com.example.conseil.entities.Recipe;
import com.example.conseil.entities.Specialist;
import com.example.conseil.enums.RecipeCategory;
import com.example.conseil.exception.RecipeNotFoundException;
import com.example.conseil.exception.ResourceNotFoundException;
import com.example.conseil.mapper.RecipeMapper;
import com.example.conseil.repository.RecipeRepository;
import com.example.conseil.repository.SpecialistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeMapper recipeMapper;
    @Autowired
    private SpecialistRepository specialistRepository;

    public RecipeDto addRecipe(RecipeDto recipeDto,Long specialist_id, byte[] imageBytes) {

        Recipe recipe = recipeMapper.recipeDtoToRecipe(recipeDto);

        Specialist specialist = specialistRepository.findById(specialist_id
        ).orElseThrow(() ->
                new ResourceNotFoundException("Specialist not found with id: " + recipeDto.getSpecialistId()));
        recipe.setDatePublication(new Date(System.currentTimeMillis()));

        recipe.setSpecialist(specialist);
        recipe.setImage(imageBytes);
        Recipe recipe1 = recipeRepository.save(recipe);
        return  recipeMapper.recipeToRecipeDto(recipe1);

    }


    public RecipeDto getRecipeById(Long id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe not found with id: " + id));
        return recipeMapper.recipeToRecipeDto(recipe);
    }

    public List<RecipeDto> getAllRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes.stream().map(recipeMapper::recipeToRecipeDto).toList();
    }

    public RecipeDto updateRecipe(Long recipeId, RecipeDto recipeDto,byte[] imageBytes) {
        Recipe existingRecipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe not found with id: " + recipeId));

        existingRecipe.setName(recipeDto.getName());
        existingRecipe.setDescription(recipeDto.getDescription());
        existingRecipe.setIngredients(recipeDto.getIngredients());
        existingRecipe.setInstructions(recipeDto.getInstructions());
        if (imageBytes != null && imageBytes.length > 0) {
            existingRecipe.setImage(imageBytes);
        }
        existingRecipe.setDatePublication(new Date(System.currentTimeMillis()));
        Recipe updatedRecipe = recipeRepository.save(existingRecipe);
        return recipeMapper.recipeToRecipeDto(updatedRecipe);
    }
    public void deleteRecipe(Long id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe not found with id: " + id));
        recipeRepository.delete(recipe);
    }

//    public List<RecipeDto> getRecipesByCategory(RecipeCategory category) {
//        List<Recipe> recipes = recipeRepository.findByCategory(category);
//        if (recipes.isEmpty()) {
//            throw new RecipeNotFoundException("No recipes found for category: " + category);
//        }
//        return recipes.stream().map(recipeMapper::recipeToRecipeDto).toList();
//    }
 public List<RecipeDto> getRecipesByCategory(RecipeCategory category) {
    List<Recipe> recipes = recipeRepository.findByCategory(category);
    if (recipes.isEmpty()) {
        throw new RecipeNotFoundException("No recipes found for category: " + category);
    }
    return recipes.stream()
            .map(recipeMapper::recipeToRecipeDto)
            .toList();
}
}
