import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {RecipeService} from "../../services/recipe.service";
import {FavrecipeService} from "../../services/favrecipe.service";
import {ActivatedRoute} from "@angular/router";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-favorit',
  template: `
    <div *ngIf="currentRecipe">
      <h2>{{ currentRecipe.name }}</h2>
      <p>{{ currentRecipe.description }}</p>

      <div class="rating">
        <span *ngFor="let star of stars; let i = index"
              (click)="rate(i + 1)"
              [class.filled]="i < currentRating">
          ★
        </span>
        <p>Average Rating: {{ averageRating | number:'1.1-1' }}</p>
      </div>
    </div>
  `,
  styles: [`
    .rating { font-size: 24px; }
    .filled { color: gold; }
  `]
})
export class FavoritComponent implements OnInit {
  currentRecipe: any; // Remplacez 'any' par une interface Recipe appropriée
  currentUser: any; // Remplacez 'any' par une interface User appropriée
  stars: number[] = [1, 2, 3, 4, 5];
  currentRating: number = 0;
  averageRating: number = 0;

  constructor(
    private route: ActivatedRoute,
    private recipeService: FavrecipeService,
    private authService: AuthService
  ) {}

  ngOnInit() {
    this.route.params.subscribe(params => {
      const recipeId = +params['id']; // Convertit l'id en nombre
      this.loadRecipe(recipeId);
    });

    this.loadFullUserDetails();
  }

  loadRecipe(id: number) {
    this.recipeService.getRecipe(id).subscribe(
      recipe => {
        this.currentRecipe = recipe;
        this.getAverageRating();
      },
      error => console.error('Error loading recipe:', error)
    );
  }



  loadFullUserDetails() {
    this.authService.getCurrentUser().subscribe(
      user => {
        // Utilisez les détails complets de l'utilisateur ici
        console.log('User details:', user);
      },
      error => console.error('Error loading full user details:', error)
    );
  }

  rate(rating: number) {
    if (!this.currentRecipe || !this.currentUser) {
      console.error('Recipe or user information is missing');
      return;
    }
    this.recipeService.addRating(this.currentRecipe.id, this.currentUser.id, rating).subscribe(
      (response) => {
        this.currentRating = rating;
        this.getAverageRating();
      },
      (error) => console.error('Error rating recipe:', error)
    );
  }

  getAverageRating() {
    if (!this.currentRecipe) {
      console.error('Recipe information is missing');
      return;
    }
    this.recipeService.getAverageRating(this.currentRecipe.id).subscribe(
      (avgRating) => this.averageRating = avgRating,
      (error) => console.error('Error getting average rating:', error)
    );
  }
}
