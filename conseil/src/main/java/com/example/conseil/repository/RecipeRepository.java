package com.example.conseil.repository;

import com.example.conseil.entities.Recipe;
import com.example.conseil.enums.RecipeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByCategory(RecipeCategory category);

}
