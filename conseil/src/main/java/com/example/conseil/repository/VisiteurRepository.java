package com.example.conseil.repository;

import com.example.conseil.entities.FavoriteRecipe;
import com.example.conseil.entities.Recipe;
import com.example.conseil.entities.Visiteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.Optional;

public interface VisiteurRepository extends JpaRepository<Visiteur, Long> {

}
