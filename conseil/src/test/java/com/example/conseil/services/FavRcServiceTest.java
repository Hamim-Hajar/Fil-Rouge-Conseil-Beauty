//package com.example.conseil.services;
//import com.example.conseil.dto.FavoriteRecipeDto;
//import com.example.conseil.entities.FavoriteRecipe;
//import com.example.conseil.entities.Recipe;
//import com.example.conseil.entities.Visiteur;
//import com.example.conseil.exception.RatingNotFoundException;
//import com.example.conseil.exception.RecipeNotFoundException;
//import com.example.conseil.exception.UserNotFoundException;
//import com.example.conseil.mapper.FavoriteRecipeMapper;
//import com.example.conseil.repository.FavoriteRecipeRepository;
//import com.example.conseil.repository.RecipeRepository;
//import com.example.conseil.repository.VisiteurRepository;
//import com.example.conseil.services.FavRcService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class FavRcServiceTest {
//    @Mock
//    private FavoriteRecipeRepository favoriteRecipeRepository;
//
//    @Mock
//    private RecipeRepository recipeRepository;
//
//    @Mock
//    private VisiteurRepository visiteurRepository;
//
//    @Mock
//    private FavoriteRecipeMapper favoriteRecipeMapper;
//
//    @InjectMocks
//    private FavRcService favRcService;
//
//    private Recipe recipe;
//    private Visiteur visiteur;
//    private FavoriteRecipe favoriteRecipe;
//    private FavoriteRecipeDto favoriteRecipeDto;
//
//    @BeforeEach
//    public void setUp() {
//        // Setup des objets de test
//        recipe = new Recipe();
//        recipe.setId(1L);
//        recipe.setName("Pasta");
//
//        visiteur = new Visiteur();
//        visiteur.setId(1L);
//        visiteur.setFullName("John Doe");
//
//        favoriteRecipe = new FavoriteRecipe();
//        favoriteRecipe.setId(1L);
//        favoriteRecipe.setRecipe(recipe);
//        favoriteRecipe.setVisiteur(visiteur);
//        favoriteRecipe.setValue(5);
//
//        favoriteRecipeDto = new FavoriteRecipeDto();
//        favoriteRecipeDto.setId(1L);
//        favoriteRecipeDto.setRecipeName("Pasta");
//        favoriteRecipeDto.setValue(5);
//    }
//
//    // Test du succès pour ajouter une évaluation
//    @Test
//    public void testAddRating_Success() {
//        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));
//        when(visiteurRepository.findById(1L)).thenReturn(Optional.of(visiteur));
//        when(favoriteRecipeRepository.findByRecipeAndVisiteur(recipe, visiteur)).thenReturn(Optional.of(favoriteRecipe));
//        when(favoriteRecipeRepository.save(favoriteRecipe)).thenReturn(favoriteRecipe);
//        when(favoriteRecipeMapper.toDto(favoriteRecipe)).thenReturn(favoriteRecipeDto);
//
//        FavoriteRecipeDto result = favRcService.addRating(1L, 1L, 5);
//
//        assertNotNull(result);
//        assertEquals(favoriteRecipeDto.getRecipeName(), result.getRecipeName());
//        assertEquals(favoriteRecipeDto.getValue(), result.getValue());
//        verify(favoriteRecipeRepository, times(1)).save(favoriteRecipe);
//    }
//
//    // Test du cas d'exception lorsque la recette n'est pas trouvée
//    @Test
//    public void testAddRating_RecipeNotFound() {
//        when(recipeRepository.findById(1L)).thenReturn(Optional.empty());
//
//        RecipeNotFoundException exception = assertThrows(RecipeNotFoundException.class, () -> {
//            favRcService.addRating(1L, 1L, 5);
//        });
//
//        assertEquals("Recipe not found with id: 1", exception.getMessage());
//        verify(favoriteRecipeRepository, never()).save(any());
//    }
//
//    // Test du cas d'exception lorsque le visiteur n'est pas trouvé
//    @Test
//    public void testAddRating_VisiteurNotFound() {
//        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));
//        when(visiteurRepository.findById(1L)).thenReturn(Optional.empty());
//
//        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
//            favRcService.addRating(1L, 1L, 5);
//        });
//
//        assertEquals("Visiteur not found with id: 1", exception.getMessage());
//        verify(favoriteRecipeRepository, never()).save(any());
//    }
//
//    // Test du succès pour récupérer la moyenne des évaluations d'une recette
//    @Test
//    public void testGetAverageRating_Success() {
//        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));
//        when(favoriteRecipeRepository.getAverageRatingForRecipe(recipe)).thenReturn(4.5);
//
//        double result = favRcService.getAverageRating(1L);
//
//        assertEquals(4.5, result);
//        verify(favoriteRecipeRepository, times(1)).getAverageRatingForRecipe(recipe);
//    }
//
//    // Test du cas d'exception lorsque la recette pour la moyenne n'est pas trouvée
//    @Test
//    public void testGetAverageRating_RecipeNotFound() {
//        when(recipeRepository.findById(1L)).thenReturn(Optional.empty());
//
//        RecipeNotFoundException exception = assertThrows(RecipeNotFoundException.class, () -> {
//            favRcService.getAverageRating(1L);
//        });
//
//        assertEquals("Recipe not found with id: 1", exception.getMessage());
//    }
//
//    // Test du succès pour la mise à jour d'une évaluation
//    @Test
//    public void testUpdateRating_Success() {
//        when(favoriteRecipeRepository.findById(1L)).thenReturn(Optional.of(favoriteRecipe));
//        when(favoriteRecipeRepository.save(favoriteRecipe)).thenReturn(favoriteRecipe);
//        when(favoriteRecipeMapper.toDto(favoriteRecipe)).thenReturn(favoriteRecipeDto);
//
//        FavoriteRecipeDto result = favRcService.updateRating(1L, 4);
//
//        assertNotNull(result);
//        assertEquals(favoriteRecipeDto.getValue(), result.getValue());
//        verify(favoriteRecipeRepository, times(1)).save(favoriteRecipe);
//    }
//
//    // Test du cas d'exception lorsque l'évaluation n'est pas trouvée pour mise à jour
//    @Test
//    public void testUpdateRating_RatingNotFound() {
//        when(favoriteRecipeRepository.findById(1L)).thenReturn(Optional.empty());
//
//        RatingNotFoundException exception = assertThrows(RatingNotFoundException.class, () -> {
//            favRcService.updateRating(1L, 4);
//        });
//
//        assertEquals("Rating not found with id: 1", exception.getMessage());
//    }
//}
