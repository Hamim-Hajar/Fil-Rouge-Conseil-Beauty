package com.example.conseil.controllers;

import com.example.conseil.dto.ArticleDto;
import com.example.conseil.dto.RecipeDto;
import com.example.conseil.entities.Recipe;
import com.example.conseil.enums.RecipeCategory;
import com.example.conseil.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/recipe")
@CrossOrigin(origins = "http://localhost:4200")
public class RecipeController {

@Autowired

private RecipeService recipeService;



    @PostMapping("/add")
    public ResponseEntity<RecipeDto> addRecipe(@RequestParam String name,
                                               @RequestParam String description,
                                                @RequestParam String ingredients,
                                               @RequestParam String instructions,
                                               @RequestParam Long specialist_id,
                                               @RequestPart("image") MultipartFile image
                                               )  throws IOException{
        byte[] imageBytes =  image.getBytes();

        RecipeDto recipeDto = RecipeDto.builder()
                .name(name)
                .description(description)
                .ingredients(ingredients)
                .instructions(instructions)
                .build();

        RecipeDto addRecipe = recipeService.addRecipe(recipeDto,specialist_id ,imageBytes );
        return new ResponseEntity<>(addRecipe, HttpStatus.CREATED);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<RecipeDto> getRecipeById(@PathVariable Long id) {
        RecipeDto recipe = recipeService.getRecipeById(id);
        return new ResponseEntity<>(recipe,HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<RecipeDto>> getAllRecipes() {
        List<RecipeDto> recipeDtos = recipeService.getAllRecipes();
        return ResponseEntity.ok(recipeDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecipeDto> updateRecipe(@PathVariable Long id, @RequestBody RecipeDto updatedRecipeDto) {
        RecipeDto updatedRecipe = recipeService.updateRecipe(id, updatedRecipeDto);
        return ResponseEntity.ok(updatedRecipe);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }}
