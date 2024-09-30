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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;
@ExtendWith(MockitoExtension.class)
public class RecipeServiceTest {
@Mock
private RecipeRepository recipeRepository;

@Mock
private SpecialistRepository specialistRepository;

@Mock
private RecipeMapper recipeMapper;

@InjectMocks
private RecipeService recipeService;

@BeforeEach
void setUp() {
    MockitoAnnotations.openMocks(this);
}

@Test
void testAddRecipe() {
    // Arrange
    RecipeDto recipeDto = new RecipeDto();
    recipeDto.setName("Test Recipe");

    Specialist specialist = new Specialist();
    specialist.setId(1L);

    Recipe recipe = new Recipe();
    recipe.setId(1L);
    recipe.setName("Test Recipe");

    when(specialistRepository.findById(1L)).thenReturn(Optional.of(specialist));
    when(recipeMapper.recipeDtoToRecipe(recipeDto)).thenReturn(recipe);
    when(recipeRepository.save(any(Recipe.class))).thenReturn(recipe);
    when(recipeMapper.recipeToRecipeDto(recipe)).thenReturn(recipeDto);

    // Act
    RecipeDto result = recipeService.addRecipe(recipeDto, 1L, new byte[]{});

    // Assert
    assertNotNull(result);
    assertEquals("Test Recipe", result.getName());
    verify(recipeRepository).save(any(Recipe.class));
}

@Test
void testGetRecipeById() {
    // Arrange
    Long recipeId = 1L;
    Recipe recipe = new Recipe();
    recipe.setId(recipeId);
    recipe.setName("Test Recipe");

    RecipeDto recipeDto = new RecipeDto();
    recipeDto.setId(recipeId);
    recipeDto.setName("Test Recipe");

    when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(recipe));
    when(recipeMapper.recipeToRecipeDto(recipe)).thenReturn(recipeDto);

    // Act
    RecipeDto result = recipeService.getRecipeById(recipeId);

    // Assert
    assertNotNull(result);
    assertEquals(recipeId, result.getId());
    assertEquals("Test Recipe", result.getName());
}

@Test
void testGetAllRecipes() {
    // Arrange
    Recipe recipe1 = new Recipe();
    Recipe recipe2 = new Recipe();
    List<Recipe> recipes = Arrays.asList(recipe1, recipe2);

    RecipeDto recipeDto1 = new RecipeDto();
    RecipeDto recipeDto2 = new RecipeDto();

    when(recipeRepository.findAll()).thenReturn(recipes);
    when(recipeMapper.recipeToRecipeDto(recipe1)).thenReturn(recipeDto1);
    when(recipeMapper.recipeToRecipeDto(recipe2)).thenReturn(recipeDto2);

    // Act
    List<RecipeDto> result = recipeService.getAllRecipes();

    // Assert
    assertEquals(2, result.size());
    verify(recipeRepository).findAll();
}

@Test
void testUpdateRecipe() {
    // Arrange
    Long recipeId = 1L;
    RecipeDto updatedRecipeDto = new RecipeDto();
    updatedRecipeDto.setName("Updated Recipe");

    Recipe existingRecipe = new Recipe();
    existingRecipe.setId(recipeId);
    existingRecipe.setName("Original Recipe");

    when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(existingRecipe));
    when(recipeRepository.save(any(Recipe.class))).thenReturn(existingRecipe);
    when(recipeMapper.recipeToRecipeDto(existingRecipe)).thenReturn(updatedRecipeDto);

    // Act
    RecipeDto result = recipeService.updateRecipe(recipeId, updatedRecipeDto);

    // Assert
    assertNotNull(result);
    assertEquals("Updated Recipe", result.getName());
    verify(recipeRepository).save(existingRecipe);
}

@Test
void testDeleteRecipe() {
    // Arrange
    Long recipeId = 1L;
    Recipe recipe = new Recipe();
    recipe.setId(recipeId);

    when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(recipe));

    // Act
    recipeService.deleteRecipe(recipeId);

    // Assert
    verify(recipeRepository).delete(recipe);
}

@Test
void testGetRecipesByCategory() {
    // Arrange
    RecipeCategory category = RecipeCategory.HAIR;
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
    assertEquals(2, result.size());
    verify(recipeRepository).findByCategory(category);
}}
