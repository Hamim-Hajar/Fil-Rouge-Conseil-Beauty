package com.example.conseil.repository;

import com.example.conseil.entities.FavoriteRecipe;
import com.example.conseil.entities.Recipe;
import com.example.conseil.entities.Visiteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FavoriteRecipeRepository extends JpaRepository<FavoriteRecipe,Long> {
    Optional<FavoriteRecipe> findByRecipeAndVisiteur(Recipe recipe, Visiteur visiteur);
    @Query("SELECT AVG(f.value) FROM FavoriteRecipe f WHERE f.recipe = :recipe")
    double getAverageRatingForRecipe(Recipe recipe);
}
