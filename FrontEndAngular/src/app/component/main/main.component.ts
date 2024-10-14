import {Component, computed, OnInit} from '@angular/core';
import {ArticleDto} from "../../dto/article-dto";
import {ArticlService} from "../../services/articl.service";
import {RecipeDto} from "../../dto/recipe-dto";
import {RecipeService} from "../../services/recipe.service";
import {AuthService} from "../../services/auth.service";
import {RecipeCategory} from "../../enums/recipe-category";
import {FavrecipeService} from "../../services/favrecipe.service";

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
  selectedCategory: RecipeCategory | null = null;
  visitorId: number = 3; // Replace with real visitor authentication
  favorites: any[] = [];
  constructor(private articleService: ArticlService, private recipeService:RecipeService,private authService :AuthService ,private favoriteService: FavrecipeService) { }

  ngOnInit(): void {
    this.fetchAllArticles();
    this.fetchAllRecipes();
    this.userRole = this.authService.getUserRole();
    console.log('User Role:', this.userRole);
    this.loadFavorites();
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
  onCategoryClick(category: RecipeCategory): void {
    this.selectedCategory = category;
    this.recipeService.getRecipesByCategory(category).subscribe(
      (data) => {
        this.listRecipe = data;
      },
      (error) => {
        console.error(`Error fetching ${category} recipes:`, error);
      }
    );
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
  loadFavorites(): void {
    this.favoriteService.getFavorites(this.visitorId).subscribe((data) => {
      this.favorites = data;
    });
  }

  addFavorite(recipeId: number): void {
    this.favoriteService.addFavorite(this.visitorId, recipeId).subscribe(() => {
      alert('Recipe added to favorites!');
      this.loadFavorites();
    });
  }

  removeFavorite(recipeId: number): void {
    this.favoriteService.removeFavorite(this.visitorId, recipeId).subscribe(() => {
      alert('Recipe removed from favorites!');
      this.loadFavorites();
    });
  }

  isFavorite(recipeId: number): boolean {
    return this.favorites.includes(recipeId);
  }


  // addFavorite(recipeId: number): void {
  //   this.favoriteService.addFavorite(this.visitorId, recipeId).subscribe({
  //     next: () => {
  //       alert('Recipe added to favorites!');
  //       this.router.navigate(['/favorites']); // Navigate to Favorites Component
  //     },
  //     error: (err) => console.error('Error adding to favorites:', err)
  //   });
  // }
  protected readonly RecipeCategory = RecipeCategory;
}
