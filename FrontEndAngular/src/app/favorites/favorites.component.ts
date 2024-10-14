import {Component, OnInit} from '@angular/core';
import {FavoriteRecipe} from "../models/favorite-recipe";
import {FavrecipeService} from "../services/favrecipe.service";
import {FavoriteRecipeDto} from "../dto/favorite-recipe-dto";
import {RecipeService} from "../services/recipe.service";

@Component({
  selector: 'app-favorites',
  templateUrl: './favorites.component.html',
  styleUrls: ['./favorites.component.css']
})
export class FavoritesComponent  implements OnInit {
  visitorId: number = 3; // Replace with real visitor authentication logic
  favorites: FavoriteRecipeDto[] = []; // Store the favorite recipes

  constructor(private favoriteService: FavrecipeService,private racipeservice:RecipeService) {}

  ngOnInit(): void {
    this.loadFavorites();
  }

  // Load the visitor's favorite recipes
  loadFavorites(): void {
    console.log('Loading favorite recipes...');

    // Step 1: Load the list of favorite recipes (with only basic info like recipeId)
    this.favoriteService.getFavorites(this.visitorId).subscribe({
      next: (favorites) => {
        console.log('Favorite recipes loaded:', favorites);

        // Step 2: For each favorite, load the full recipe details
        this.favorites = []; // Clear current list to avoid duplication

        favorites.forEach((favorite) => {
          this.loadRecipeDetails(favorite.recipeId, favorite); // Load full recipe
        });
      },
      error: (err) => {
        console.error('Error loading favorite recipes:', err); // Handle errors gracefully
      }
    });


}
  loadRecipeDetails(recipeId: number, favorite: any): void {
    this.racipeservice.getRecipeById(recipeId).subscribe({
      next: (recipe) => {
        console.log('Full Recipe Details:', recipe);

        // Merge the full recipe details into the favorite object
        favorite.recipe = recipe;

        // Push the updated favorite object to the favorites list
        this.favorites.push(favorite);
      },
      error: (err) => {
        console.error(`Error loading recipe with ID ${recipeId}:`, err);
      }
    });
  }


}
