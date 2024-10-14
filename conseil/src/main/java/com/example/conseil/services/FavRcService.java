package com.example.conseil.services;

import com.example.conseil.dto.FavoriteRecipeDto;
import com.example.conseil.entities.FavoriteRecipe;
import com.example.conseil.entities.Recipe;
import com.example.conseil.entities.Visiteur;
import com.example.conseil.exception.RatingNotFoundException;
import com.example.conseil.exception.RecipeNotFoundException;
import com.example.conseil.exception.ResourceNotFoundException;
import com.example.conseil.exception.UserNotFoundException;
import com.example.conseil.mapper.FavoriteRecipeMapper;
import com.example.conseil.repository.FavoriteRecipeRepository;
import com.example.conseil.repository.RecipeRepository;
import com.example.conseil.repository.VisiteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void addFavoriteRecipe(Long visiteurId, Long recipeId) {
        if (favoriteRecipeRepository.existsByVisiteurIdAndRecipeId(visiteurId, recipeId)) {
            throw new IllegalStateException("Recipe is already a favorite.");
        }

        Visiteur visiteur = visiteurRepository.findById(visiteurId)
                .orElseThrow(() -> new ResourceNotFoundException("Visitor not found with id: " + visiteurId));
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe not found with id: " + recipeId));

        FavoriteRecipe favorite = new FavoriteRecipe();
        favorite.setRecipeName(recipe.getName());
        favorite.setRecipe(recipe);
        favorite.setVisiteur(visiteur);

        favoriteRecipeRepository.save(favorite);
    }

    public void removeFavoriteRecipe(Long visiteurId, Long recipeId) {
        FavoriteRecipe favorite = favoriteRecipeRepository.findByVisiteurIdAndRecipeId(visiteurId, recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Favorite recipe not found."));
        favoriteRecipeRepository.delete(favorite);
    }

    public List<FavoriteRecipe> getFavoritesByVisiteur(Long visiteurId) {
        return favoriteRecipeRepository.findByVisiteurId(visiteurId);
    }
}