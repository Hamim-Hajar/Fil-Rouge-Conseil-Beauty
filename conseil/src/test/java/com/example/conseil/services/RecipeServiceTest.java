package com.example.conseil.services;

/*import com.example.conseil.dto.RecipeDto;
import com.example.conseil.entities.Recipe;
import com.example.conseil.enums.RecipeCategory;
import com.example.conseil.exception.RecipeNotFoundException;
import com.example.conseil.mapper.RecipeMapper;
import com.example.conseil.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
class RecipeServiceTest {

    private RecipeService recipeService;
    private RecipeRepository recipeRepository;
    private RecipeMapper recipeMapper;

    @BeforeEach
    void setUp() {
        recipeRepository = mock(RecipeRepository.class);
        recipeMapper = mock(RecipeMapper.class);
        recipeService = new RecipeService(recipeRepository, recipeMapper);  // Utilisation du constructeur
    }

    @Test
    void addRecipe_ShouldReturnSavedRecipeDto() {
        // Arrange
        RecipeDto recipeDto = new RecipeDto();
        Recipe recipe = new Recipe();
        Recipe savedRecipe = new Recipe();
        RecipeDto savedRecipeDto = new RecipeDto();

        when(recipeMapper.toEntity(recipeDto)).thenReturn(recipe);
        when(recipeRepository.save(recipe)).thenReturn(savedRecipe);
        when(recipeMapper.toDto(savedRecipe)).thenReturn(savedRecipeDto);

        // Act
        RecipeDto result = recipeService.addRecipe(recipeDto);

        // Assert
        assertNotNull(result);
        verify(recipeRepository).save(recipe);
        verify(recipeMapper).toDto(savedRecipe);
    }

    @Test
    void getRecipeById_ShouldReturnRecipeDto_WhenRecipeExists() {
        // Arrange
        Long recipeId = 1L;
        Recipe recipe = new Recipe();
        RecipeDto recipeDto = new RecipeDto();

        when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(recipe));
        when(recipeMapper.toDto(recipe)).thenReturn(recipeDto);

        // Act
        RecipeDto result = recipeService.getRecipeById(recipeId);

        // Assert
        assertNotNull(result);
        assertEquals(recipeDto, result);
        verify(recipeRepository).findById(recipeId);
        verify(recipeMapper).toDto(recipe);
    }

    @Test
    void getRecipeById_ShouldThrowRecipeNotFoundException_WhenRecipeDoesNotExist() {
        // Arrange
        Long recipeId = 1L;

        when(recipeRepository.findById(recipeId)).thenReturn(Optional.empty());

        // Act & Assert
        RecipeNotFoundException exception = assertThrows(RecipeNotFoundException.class, () -> {
            recipeService.getRecipeById(recipeId);
        });

        assertEquals("Recipe not found with id: 1", exception.getMessage());
    }

    @Test
    void getAllRecipes_ShouldReturnListOfRecipeDtos() {
        // Arrange
        List<Recipe> recipes = List.of(new Recipe());
        List<RecipeDto> recipeDtos = List.of(new RecipeDto());

        when(recipeRepository.findAll()).thenReturn(recipes);
        when(recipeMapper.toDto(any(Recipe.class))).thenReturn(recipeDtos.get(0));

        // Act
        List<RecipeDto> result = recipeService.getAllRecipes();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(recipeRepository).findAll();
    }

    @Test
    void updateRecipe_ShouldReturnUpdatedRecipeDto_WhenRecipeExists() {
        // Arrange
        Long recipeId = 1L;
        RecipeDto updatedRecipeDto = new RecipeDto();
        Recipe existingRecipe = new Recipe();
        Recipe updatedRecipe = new Recipe();
        RecipeDto resultRecipeDto = new RecipeDto();

        when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(existingRecipe));
        when(recipeRepository.save(existingRecipe)).thenReturn(updatedRecipe);
        when(recipeMapper.toDto(updatedRecipe)).thenReturn(resultRecipeDto);

        // Act
        RecipeDto result = recipeService.updateRecipe(recipeId, updatedRecipeDto);

        // Assert
        assertNotNull(result);
        verify(recipeRepository).findById(recipeId);
        verify(recipeRepository).save(existingRecipe);
        verify(recipeMapper).toDto(updatedRecipe);
    }

    @Test
    void updateRecipe_ShouldThrowRecipeNotFoundException_WhenRecipeDoesNotExist() {
        // Arrange
        Long recipeId = 1L;
        RecipeDto updatedRecipeDto = new RecipeDto();

        when(recipeRepository.findById(recipeId)).thenReturn(Optional.empty());

        // Act & Assert
        RecipeNotFoundException exception = assertThrows(RecipeNotFoundException.class, () -> {
            recipeService.updateRecipe(recipeId, updatedRecipeDto);
        });

        assertEquals("Recipe not found with id: 1", exception.getMessage());
    }

    @Test
    void deleteRecipe_ShouldDeleteRecipe_WhenRecipeExists() {
        // Arrange
        Long recipeId = 1L;
        Recipe recipe = new Recipe();

        when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(recipe));

        // Act
        recipeService.deleteRecipe(recipeId);

        // Assert
        verify(recipeRepository).delete(recipe);
    }

    @Test
    void deleteRecipe_ShouldThrowRecipeNotFoundException_WhenRecipeDoesNotExist() {
        // Arrange
        Long recipeId = 1L;

        when(recipeRepository.findById(recipeId)).thenReturn(Optional.empty());

        // Act & Assert
        RecipeNotFoundException exception = assertThrows(RecipeNotFoundException.class, () -> {
            recipeService.deleteRecipe(recipeId);
        });

        assertEquals("Recipe not found with id: 1", exception.getMessage());
    }

    @Test
    void getRecipesByCategory_ShouldReturnListOfRecipes_WhenRecipesExist() {
        // Arrange
        RecipeCategory category = RecipeCategory.FACE;
        List<Recipe> recipes = List.of(new Recipe());
        List<RecipeDto> recipeDtos = List.of(new RecipeDto());

        when(recipeRepository.findByCategory(category)).thenReturn(recipes);
        when(recipeMapper.toDto(any(Recipe.class))).thenReturn(recipeDtos.get(0));

        // Act
        List<RecipeDto> result = recipeService.getRecipesByCategory(category);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(recipeRepository).findByCategory(category);
    }

    @Test
    void getRecipesByCategory_ShouldThrowRecipeNotFoundException_WhenNoRecipesFound() {
        // Arrange
        RecipeCategory category = RecipeCategory.FACE;

        when(recipeRepository.findByCategory(category)).thenReturn(Collections.emptyList());

        // Act & Assert
        RecipeNotFoundException exception = assertThrows(RecipeNotFoundException.class, () -> {
            recipeService.getRecipesByCategory(category);
        });

        assertEquals("No recipes found for category: VEGETARIAN", exception.getMessage());
    }
}*/