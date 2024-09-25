package com.example.conseil.services;

import com.example.conseil.dto.RecipeDto;
import com.example.conseil.entities.Recipe;
import com.example.conseil.enums.RecipeCategory;
import com.example.conseil.exception.RecipeNotFoundException;
import com.example.conseil.mapper.RecipeMapper;
import com.example.conseil.repository.RecipeRepository;
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

@ExtendWith(MockitoExtension.class)
public class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private RecipeMapper recipeMapper;

    @InjectMocks
    private RecipeService recipeService;

    private Recipe recipe;
    private RecipeDto recipeDto;

    @BeforeEach
    public void setUp() {
        // Setup a sample Recipe and RecipeDto
        recipe = new Recipe();
        recipe.setId(1L);
        recipe.setName("Pasta");
        recipe.setDescription("Delicious pasta recipe");
        recipe.setCategory(RecipeCategory.HAIR);
        recipe.setIngredients("Pasta, Tomatoes, Cheese");

        recipeDto = new RecipeDto();
        recipeDto.setId(1L);
        recipeDto.setName("Pasta");
        recipeDto.setDescription("Delicious pasta recipe");
        recipeDto.setCategory(RecipeCategory.HAIR);
        recipeDto.setIngredients("Pasta, Tomatoes, Cheese");
    }

    // Test du succès pour l'ajout d'une recette
    @Test
    public void testAddRecipe_Success() {
        when(recipeMapper.toEntity(recipeDto)).thenReturn(recipe);
        when(recipeRepository.save(recipe)).thenReturn(recipe);
        when(recipeMapper.toDto(recipe)).thenReturn(recipeDto);

        RecipeDto result = recipeService.addRecipe(recipeDto);

        assertNotNull(result);
        assertEquals(recipeDto.getName(), result.getName());
        verify(recipeRepository, times(1)).save(recipe);
    }

    // Test du succès pour la récupération d'une recette par ID
    @Test
    public void testGetRecipeById_Success() {
        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));
        when(recipeMapper.toDto(recipe)).thenReturn(recipeDto);

        RecipeDto result = recipeService.getRecipeById(1L);

        assertNotNull(result);
        assertEquals(recipeDto.getName(), result.getName());
        verify(recipeRepository, times(1)).findById(1L);
    }

    // Test du cas d'exception lorsque la recette n'est pas trouvée
    @Test
    public void testGetRecipeById_NotFound() {
        when(recipeRepository.findById(1L)).thenReturn(Optional.empty());

        RecipeNotFoundException exception = assertThrows(RecipeNotFoundException.class, () -> {
            recipeService.getRecipeById(1L);
        });

        assertEquals("Recipe not found with id: 1", exception.getMessage());
    }

    // Test du succès pour la récupération de toutes les recettes
    @Test
    public void testGetAllRecipes_Success() {
        when(recipeRepository.findAll()).thenReturn(Arrays.asList(recipe));
        when(recipeMapper.toDto(recipe)).thenReturn(recipeDto);

        List<RecipeDto> result = recipeService.getAllRecipes();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(recipeDto.getName(), result.get(0).getName());
        verify(recipeRepository, times(1)).findAll();
    }

    // Test du succès pour la mise à jour d'une recette
    @Test
    public void testUpdateRecipe_Success() {
        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));
        when(recipeRepository.save(recipe)).thenReturn(recipe);
        when(recipeMapper.toDto(recipe)).thenReturn(recipeDto);

        RecipeDto result = recipeService.updateRecipe(1L, recipeDto);

        assertNotNull(result);
        assertEquals(recipeDto.getName(), result.getName());
        verify(recipeRepository, times(1)).save(recipe);
    }

    // Test du cas d'exception lors de la mise à jour d'une recette non trouvée
    @Test
    public void testUpdateRecipe_NotFound() {
        when(recipeRepository.findById(1L)).thenReturn(Optional.empty());

        RecipeNotFoundException exception = assertThrows(RecipeNotFoundException.class, () -> {
            recipeService.updateRecipe(1L, recipeDto);
        });

        assertEquals("Recipe not found with id: 1", exception.getMessage());
    }

    // Test du succès pour la suppression d'une recette
    @Test
    public void testDeleteRecipe_Success() {
        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));

        recipeService.deleteRecipe(1L);

        verify(recipeRepository, times(1)).delete(recipe);
    }

    // Test du cas d'exception lors de la suppression d'une recette non trouvée
    @Test
    public void testDeleteRecipe_NotFound() {
        when(recipeRepository.findById(1L)).thenReturn(Optional.empty());

        RecipeNotFoundException exception = assertThrows(RecipeNotFoundException.class, () -> {
            recipeService.deleteRecipe(1L);
        });

        assertEquals("Recipe not found with id: 1", exception.getMessage());
    }

    // Test du succès pour la récupération des recettes par catégorie
    @Test
    public void testGetRecipesByCategory_Success() {
        when(recipeRepository.findByCategory(RecipeCategory.HAIR)).thenReturn(Arrays.asList(recipe));
        when(recipeMapper.toDto(recipe)).thenReturn(recipeDto);

        List<RecipeDto> result = recipeService.getRecipesByCategory(RecipeCategory.HAIR);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(recipeDto.getCategory(), result.get(0).getCategory());
        verify(recipeRepository, times(1)).findByCategory(RecipeCategory.HAIR);
    }

    // Test du cas d'exception lorsqu'aucune recette n'est trouvée pour une catégorie donnée
    @Test
    public void testGetRecipesByCategory_NotFound() {
        when(recipeRepository.findByCategory(RecipeCategory.HAIR)).thenReturn(Arrays.asList());

        RecipeNotFoundException exception = assertThrows(RecipeNotFoundException.class, () -> {
            recipeService.getRecipesByCategory(RecipeCategory.HAIR      );
        });

        assertEquals("No recipes found for category: MAIN_COURSE", exception.getMessage());
    }
}
