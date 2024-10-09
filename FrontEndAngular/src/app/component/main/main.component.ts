import {Component, computed, OnInit} from '@angular/core';
import {ArticleDto} from "../../dto/article-dto";
import {ArticlService} from "../../services/articl.service";
import {RecipeDto} from "../../dto/recipe-dto";
import {RecipeService} from "../../services/recipe.service";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit{
  userRole: string = '';
  listArticle: ArticleDto[] = [];
  selectedArticle: ArticleDto | null = null;
  listRecipe:RecipeDto []=[];
  selectedRecipe:RecipeDto | null = null;
  constructor(private articleService: ArticlService, private recipeService:RecipeService,private authService :AuthService ) { }

  ngOnInit(): void {
    this.fetchAllArticles();
    this.fetchAllRecipes();
    this.userRole = this.authService.getUserRole();
    console.log('User Role:', this.userRole);
  }

  fetchAllArticles() {
    this.articleService.getAllArticles().subscribe((res: ArticleDto[]) => {
      this.listArticle = res;
      console.log(res);
    });
  }
  fetchAllRecipes(){
    this.recipeService.getAllRecipes().subscribe((res:RecipeDto[])=>{
      this.listRecipe=res;
      console.log(res);

    });
  }


  showMoreDetails(articleId: number) {
    this.articleService.getArticleById(articleId).subscribe((article: ArticleDto) => {
      this.selectedArticle = article; // Save the selected article details
      console.log(this.selectedArticle);
      // Optionally, you could navigate to a details page or display a modal with article details.
    });
  }
  // showMoreDetailsrecipe(recipeId: number) {
  //   this.recipeService.getRecipeById(recipeId).subscribe((recipe: RecipeDto) => {
  //     this.selectedRecipe = recipe; // Save the selected article details
  //     console.log(this.selectedRecipe);
  //     // Optionally, you could navigate to a details page or display a modal with article details.
  //   });
  // }


}
