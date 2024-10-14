package com.example.conseil.repository;

import com.example.conseil.entities.FavoriteRecipe;
import com.example.conseil.entities.Recipe;
import com.example.conseil.entities.Visiteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FavoriteRecipeRepository extends JpaRepository<FavoriteRecipe,Long> {

    @Query("SELECT f FROM FavoriteRecipe f WHERE f.visiteur.id = :visiteurId AND f.recipe.id = :recipeId")
    Optional<FavoriteRecipe> findByVisiteurIdAndRecipeId(@Param("visiteurId") Long visiteurId,
                                                         @Param("recipeId") Long recipeId);

    List<FavoriteRecipe> findByVisiteurId(Long visiteurId);
    boolean existsByVisiteurIdAndRecipeId(Long visiteurId, Long recipeId);
}
