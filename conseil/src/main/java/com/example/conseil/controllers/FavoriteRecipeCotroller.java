package com.example.conseil.controllers;

import com.example.conseil.dto.FavoriteRecipeDto;
import com.example.conseil.entities.FavoriteRecipe;
import com.example.conseil.services.FavRcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@CrossOrigin(origins = "http://localhost:4200")
public class FavoriteRecipeCotroller {

    @Autowired
    private FavRcService favRcService;


    @PostMapping("/{visiteurId}/{recipeId}")
    public String addFavorite(@PathVariable Long visiteurId, @PathVariable Long recipeId) {
        favRcService.addFavoriteRecipe(visiteurId, recipeId);
        return "Recipe added to favorites.";
    }

    @DeleteMapping("/{visiteurId}/{recipeId}")
    public String removeFavorite(@PathVariable Long visiteurId, @PathVariable Long recipeId) {
        favRcService.removeFavoriteRecipe(visiteurId, recipeId);
        return "Recipe removed from favorites.";
    }

    @GetMapping("/{visiteurId}")
    public List<FavoriteRecipe> getFavorites(@PathVariable Long visiteurId) {
        return favRcService.getFavoritesByVisiteur(visiteurId);
    }
}