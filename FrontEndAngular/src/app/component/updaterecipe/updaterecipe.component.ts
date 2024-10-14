import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ArticlService} from "../../services/articl.service";
import {ActivatedRoute} from "@angular/router";
import {jwtDecode} from "jwt-decode";
import {ArticleDto} from "../../dto/article-dto";
import {RecipeService} from "../../services/recipe.service";
import {RecipeDto} from "../../dto/recipe-dto";
import {AuthService} from "../../services/auth.service";

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
  userRole: string = '';

  constructor(
    private fb: FormBuilder,
    private recipeservice: RecipeService,
    private route: ActivatedRoute,
    private authService :AuthService
) {

    this.formRecipe = this.fb.group({
      name: ["", Validators.required],
      description: ["", Validators.required],
      ingredients:["",Validators.required],
      instructions:["",Validators.required],
      category:["",Validators.required],
      image: [null]
    });
  }

  ngOnInit(): void {
    this.getId();
    this.fetchIdUpdate();
    this.userRole = this.authService.getUserRole();
    console.log('User Role:', this.userRole);
  }
  getId() {
    const token: string | null = localStorage.getItem("token");
    if (token) {
      const decodeToken: any = jwtDecode(token);
      this.specialist_id = decodeToken.id;
      console.log(this.specialist_id);
    }
  }


  onSubmit() {
    if (this.formRecipe.valid) {
      const formData = new FormData();
      formData.append('name', this.formRecipe.value.name);
      formData.append('description', this.formRecipe.value.description);
      formData.append('ingredients', this.formRecipe.value.ingredients);
      formData.append('instructions', this.formRecipe.value.instructions);
      formData.append('category',this.formRecipe.value.category);

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


  onFileChange(event: any) {
    if (event.target.files.length > 0) {
      this.selectedImage = event.target.files[0];
    }
  }


  fetchIdUpdate() {
    this.route.params.subscribe(params => {
      this.recipeId = +params['id'];
      if (this.recipeId) {
        this.loadArticle();
      }
    });
  }
  loadArticle(): void {
    this.recipeservice.getRecipeById(this.recipeId).subscribe(
      (recipe: RecipeDto) => {

        this.formRecipe.patchValue({
          id: recipe.id,
          name: recipe.name,
          ingredients: recipe.ingredients,
          instructions: recipe.instructions,
          description: recipe.description,
          category:recipe.category,

        });
      },
      error => {
        console.error('Error when upload the recipe', error);
      }
    );
  }

}
