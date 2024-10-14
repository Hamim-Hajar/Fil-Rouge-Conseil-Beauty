package com.example.conseil.services;

import com.example.conseil.dto.RecipeDto;
import com.example.conseil.entities.Recipe;
import com.example.conseil.entities.Specialist;
import com.example.conseil.enums.RecipeCategory;
import com.example.conseil.exception.RecipeNotFoundException;
import com.example.conseil.mapper.RecipeMapper;
import com.example.conseil.repository.RecipeRepository;
import com.example.conseil.repository.SpecialistRepository;
import com.example.conseil.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private RecipeMapper recipeMapper;

    @InjectMocks
    private RecipeService recipeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getRecipesByCategory_shouldReturnListOfRecipeDtos() {
        // Arrange
        RecipeCategory category = RecipeCategory.FACE;
        Recipe recipe1 = new Recipe();
        Recipe recipe2 = new Recipe();
        List<Recipe> recipes = Arrays.asList(recipe1, recipe2);

        RecipeDto recipeDto1 = new RecipeDto();
        RecipeDto recipeDto2 = new RecipeDto();

        when(recipeRepository.findByCategory(category)).thenReturn(recipes);
        when(recipeMapper.recipeToRecipeDto(recipe1)).thenReturn(recipeDto1);
        when(recipeMapper.recipeToRecipeDto(recipe2)).thenReturn(recipeDto2);

        // Act
        List<RecipeDto> result = recipeService.getRecipesByCategory(category);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(recipeRepository, times(1)).findByCategory(category);
        verify(recipeMapper, times(2)).recipeToRecipeDto(any(Recipe.class));
    }

    @Test
    void getRecipesByCategory_shouldThrowExceptionWhenNoRecipesFound() {
        // Arrange
        RecipeCategory category = RecipeCategory.HAIR;
        when(recipeRepository.findByCategory(category)).thenReturn(List.of());

        // Act & Assert
        assertThrows(RecipeNotFoundException.class, () -> {
            recipeService.getRecipesByCategory(category);
        });
        verify(recipeRepository, times(1)).findByCategory(category);
    }}
