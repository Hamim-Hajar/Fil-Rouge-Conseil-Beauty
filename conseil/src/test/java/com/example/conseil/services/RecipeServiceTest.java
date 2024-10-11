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
    recipeDto.setSpecialistId(1L);

    Specialist specialist = new Specialist();
    specialist.setId(1L);

    Recipe recipe = new Recipe();
    recipe.setName("Test Recipe");

    Recipe savedRecipe = new Recipe();
    savedRecipe.setId(1L);
    savedRecipe.setName("Test Recipe");

    when(specialistRepository.findById(1L)).thenReturn(Optional.of(specialist));
    when(recipeMapper.recipeDtoToRecipe(recipeDto)).thenReturn(recipe);
    when(recipeRepository.save(any(Recipe.class))).thenReturn(savedRecipe);
    when(recipeMapper.recipeToRecipeDto(savedRecipe)).thenReturn(recipeDto);

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
    recipe1.setId(1L);
    recipe1.setName("Recipe 1");

    Recipe recipe2 = new Recipe();
    recipe2.setId(2L);
    recipe2.setName("Recipe 2");

    List<Recipe> recipes = Arrays.asList(recipe1, recipe2);

    RecipeDto recipeDto1 = new RecipeDto();
    recipeDto1.setId(1L);
    recipeDto1.setName("Recipe 1");

    RecipeDto recipeDto2 = new RecipeDto();
    recipeDto2.setId(2L);
    recipeDto2.setName("Recipe 2");

    when(recipeRepository.findAll()).thenReturn(recipes);
    when(recipeMapper.recipeToRecipeDto(recipe1)).thenReturn(recipeDto1);
    when(recipeMapper.recipeToRecipeDto(recipe2)).thenReturn(recipeDto2);

    // Act
    List<RecipeDto> result = recipeService.getAllRecipes();

    // Assert
    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals("Recipe 1", result.get(0).getName());
    assertEquals("Recipe 2", result.get(1).getName());
}

@Test
void testUpdateRecipe() {
    // Arrange
    Long recipeId = 1L;
    RecipeDto recipeDto = new RecipeDto();
    recipeDto.setName("Updated Recipe");
    recipeDto.setDescription("Updated Description");

    Recipe existingRecipe = new Recipe();
    existingRecipe.setId(recipeId);
    existingRecipe.setName("Original Recipe");

    Recipe updatedRecipe = new Recipe();
    updatedRecipe.setId(recipeId);
    updatedRecipe.setName("Updated Recipe");
    updatedRecipe.setDescription("Updated Description");

    when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(existingRecipe));
    when(recipeRepository.save(any(Recipe.class))).thenReturn(updatedRecipe);
    when(recipeMapper.recipeToRecipeDto(updatedRecipe)).thenReturn(recipeDto);

    // Act
    RecipeDto result = recipeService.updateRecipe(recipeId, recipeDto, new byte[]{});

    // Assert
    assertNotNull(result);
    assertEquals("Updated Recipe", result.getName());
    assertEquals("Updated Description", result.getDescription());
    verify(recipeRepository).save(any(Recipe.class));
}}
