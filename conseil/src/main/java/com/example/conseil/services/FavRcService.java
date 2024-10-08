package com.example.conseil.services;

import com.example.conseil.dto.FavoriteRecipeDto;
import com.example.conseil.entities.FavoriteRecipe;
import com.example.conseil.entities.Recipe;
import com.example.conseil.entities.Visiteur;
import com.example.conseil.exception.RatingNotFoundException;
import com.example.conseil.exception.RecipeNotFoundException;
import com.example.conseil.exception.UserNotFoundException;
import com.example.conseil.mapper.FavoriteRecipeMapper;
import com.example.conseil.repository.FavoriteRecipeRepository;
import com.example.conseil.repository.RecipeRepository;
import com.example.conseil.repository.VisiteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavRcService {
    @Autowired
    private FavoriteRecipeRepository favoriteRecipeRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private VisiteurRepository visiteurRepository;

    @Autowired
    private FavoriteRecipeMapper favoriteRecipeMapper;

    public FavoriteRecipeDto addRating(Long recipeId, Long visiteurId, int value) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe not found with id: " + recipeId));
        Visiteur visiteur = visiteurRepository.findById(visiteurId)
                .orElseThrow(() -> new UserNotFoundException("Visiteur not found with id: " + visiteurId));

        FavoriteRecipe rating = favoriteRecipeRepository.findByRecipeAndVisiteur(recipe, visiteur)
                .orElse(new FavoriteRecipe());
        rating.setRecipe(recipe);
        rating.setVisiteur(visiteur);
        rating.setValue(value);
        rating.setRecipeName(recipe.getName());

        FavoriteRecipe savedRating = favoriteRecipeRepository.save(rating);
        return favoriteRecipeMapper.toDto(savedRating);
    }

    public double getAverageRating(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe not found with id: " + recipeId));
        return favoriteRecipeRepository.getAverageRatingForRecipe(recipe);
    }

    public FavoriteRecipeDto updateRating(Long ratingId, int newValue) {
        FavoriteRecipe rating = favoriteRecipeRepository.findById(ratingId)
                .orElseThrow(() -> new RatingNotFoundException("Rating not found with id: " + ratingId));
        rating.setValue(newValue);
        FavoriteRecipe updatedRating = favoriteRecipeRepository.save(rating);
        return favoriteRecipeMapper.toDto(updatedRating);
    }
}
