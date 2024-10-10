import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ArticlService} from "../../services/articl.service";
import {ActivatedRoute} from "@angular/router";
import {jwtDecode} from "jwt-decode";
import {ArticleDto} from "../../dto/article-dto";
import {RecipeService} from "../../services/recipe.service";

@Component({
  selector: 'app-updaterecipe',
  templateUrl: './updaterecipe.component.html',
  styleUrls: ['./updaterecipe.component.css']
})
export class UpdaterecipeComponent implements OnInit{
  formRecipe!: FormGroup;
  specialist_id!: number; // Déclaration de specialist_id
  selectedImage!: File | null; // Utiliser null par défaut
  recipeId!: number;

  constructor(
    private fb: FormBuilder,
    private recipeservice: RecipeService,
    private route: ActivatedRoute
) {

    this.formRecipe = this.fb.group({
      name: ["", Validators.required],
      description: ["", Validators.required],
      ingredients:["",Validators.required],
      instructions:["",Validators.required],
      image: [null]
    });
  }

  ngOnInit(): void {
    this.getId();
    this.fetchIdUpdate();
  }
  getId() {
    const token: string | null = localStorage.getItem("token");
    if (token) {
      const decodeToken: any = jwtDecode(token);
      this.specialist_id = decodeToken.id;
      console.log(this.specialist_id);
    }
  }

  // Méthode de mise à jour de l'article
  onSubmit() {
    if (this.formRecipe.valid) {
      const formData = new FormData();
      formData.append('name', this.formRecipe.value.name);
      formData.append('description', this.formRecipe.value.description);
      formData.append('ingredients', this.formRecipe.value.ingredients);
      formData.append('instructions', this.formRecipe.value.instructions);

      if (this.selectedImage) {
        formData.append('image', this.selectedImage);
      }

      // Ensure that updateArticle method accepts number and FormData
      this.recipeservice.updateRecipe(formData,this.recipeId).subscribe(
        (response) => {
          console.log('Article mis à jour avec succès', response);
          this.formRecipe.reset();
        },
        (error) => {
          console.error('Eroor when update the recipe', error);
        }
      );
    } else {
      console.log('invalid Form');
    }
  }

  // Méthode pour gérer le changement d'image
  onFileChange(event: any) {
    if (event.target.files.length > 0) {
      this.selectedImage = event.target.files[0]; // Récupérer l'image sélectionnée
    }
  }

  // Récupérer l'ID de l'article à mettre à jour depuis l'URL
  fetchIdUpdate() {
    this.route.params.subscribe(params => {
      this.articleId = +params['id']; // Convertir en nombre
      if (this.articleId) {
        this.loadArticle(); // Charger les détails de l'article si `articleId` existe
      }
    });
  }

  // Charger les détails de l'article à partir de l'ID
  loadArticle(): void {
    this.articleService.getArticleById(this.articleId).subscribe(
      (article: ArticleDto) => {
        // Patch les valeurs du formulaire avec les détails de l'article
        this.formArticle.patchValue({
          id: article.id,
          titre: article.titre,
          contenu: article.contenu
          // L'image n'est pas préremplie car ce n'est pas possible avec un input de type file
        });
      },
      error => {
        console.error('Erreur lors de la récupération de l\'article', error);
      }
    );
  }

}
