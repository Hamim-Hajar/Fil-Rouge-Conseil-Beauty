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
                                               @RequestParam RecipeCategory category,
                                               @RequestParam Long specialist_id,
                                               @RequestPart("image") MultipartFile image
                                               )  throws IOException{
        byte[] imageBytes =  image.getBytes();

        RecipeDto recipeDto = RecipeDto.builder()
                .name(name)
                .description(description)
                .ingredients(ingredients)
                .instructions(instructions)
                .category(category)
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

    @PutMapping("/update/{id}")
    public ResponseEntity<RecipeDto> updateRecipe(@PathVariable Long id,
                                                  @RequestParam("name") String name,
                                                  @RequestParam("ingredients") String ingredients,
                                                  @RequestParam("category") RecipeCategory category,
                                                  @RequestParam("instructions") String instructions,
                                                  @RequestParam("description") String description,
                                                  @RequestParam(value = "image", required = false) MultipartFile image)
     {
         // Convert MultipartFile to byte[] if image is provided
         byte[] imageBytes = null;
         if (image != null && !image.isEmpty()) {
             try {
                 imageBytes = image.getBytes();
             } catch (Exception e) {
                 throw new RuntimeException("Failed to read image", e);
             }
         }
         RecipeDto recipeDto = new RecipeDto();
         recipeDto.setName(name);
         recipeDto.setIngredients(ingredients);
         recipeDto.setInstructions(instructions);
         recipeDto.setDescription(description);
         recipeDto.setCategory(category);


        RecipeDto updatedRecipe = recipeService.updateRecipe(id, recipeDto, imageBytes);
        return ResponseEntity.ok(updatedRecipe);
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/category/{category}")
    public ResponseEntity<List<RecipeDto>> getRecipesByCategory(
            @PathVariable RecipeCategory category) {
        List<RecipeDto> recipes = recipeService.getRecipesByCategory(category);
        return ResponseEntity.ok(recipes);
    }

}
