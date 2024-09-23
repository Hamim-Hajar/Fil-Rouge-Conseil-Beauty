import { Component } from '@angular/core';
import {Recipe} from "../../models/recipe";
import {RecipeService} from "../../services/recipe.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-addrecipe',
  templateUrl: './addrecipe.component.html',
  styleUrls: ['./addrecipe.component.css']
})
export class AddrecipeComponent {
  recipe: Recipe = {} as Recipe; // Déclaration de la recette sans initialisation
  selectedFile: File | null = null;  // Si tu utilises un champ pour télécharger une image

  constructor(private recipeService: RecipeService, private router: Router) {}

  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0];  // Gestion de la sélection d'une image
  }

  onSubmit(): void {
    // Si tu as besoin de gérer l'image avant de soumettre
    if (this.selectedFile) {
      const reader = new FileReader();
      reader.readAsDataURL(this.selectedFile);
      reader.onload = () => {
        this.recipe.image = reader.result as string; // Encodage de l'image en base64 si nécessaire
        this.addRecipe();
      };
    } else {
      this.addRecipe();  // Si pas d'image, soumission directe
    }
  }

  private addRecipe(): void {
    this.recipeService.addRecipe(this.recipe).subscribe(
      (response) => {
        console.log('Recette ajoutée avec succès', response);
        //this.router.navigate(['/recipes']);  // Redirection après l'ajout
      },
      (error) => {
        console.error('Erreur lors de l\'ajout de la recette :', error);
      }
    );
  }
}
