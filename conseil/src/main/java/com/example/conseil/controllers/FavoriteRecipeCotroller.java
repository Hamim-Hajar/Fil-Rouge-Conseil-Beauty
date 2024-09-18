package com.example.conseil.controllers;

import com.example.conseil.dto.FavoriteRecipeDto;
import com.example.conseil.entities.FavoriteRecipe;
import com.example.conseil.services.FavRcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipe")
@CrossOrigin(origins = "http://localhost:4200")
public class FavoriteRecipeCotroller {

    @Autowired
    private FavRcService favRcService;

    @PostMapping("/{recipeId}/ratings")
    public ResponseEntity<FavoriteRecipeDto> addRating(
            @PathVariable Long recipeId,
            @RequestParam Long visiteurId,
            @RequestParam int value) {
        FavoriteRecipeDto rating = favRcService.addRating(recipeId, visiteurId, value);
        return new ResponseEntity<>(rating, HttpStatus.CREATED);
    }

    @GetMapping("/{recipeId}/average-rating")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long recipeId) {
        double averageRating = favRcService.getAverageRating(recipeId);
        return new ResponseEntity<>(averageRating, HttpStatus.OK);
    }

    @PutMapping("/ratings/{ratingId}")
    public ResponseEntity<FavoriteRecipeDto> updateRating(
            @PathVariable Long ratingId,
            @RequestParam int newValue) {
        FavoriteRecipeDto updatedRating = favRcService.updateRating(ratingId, newValue);
        return new ResponseEntity<>(updatedRating, HttpStatus.OK);
    }
}
