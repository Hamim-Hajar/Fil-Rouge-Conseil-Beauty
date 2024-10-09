import {Component, OnInit} from '@angular/core';
import {ArticleDto} from "../../dto/article-dto";
import {ArticlService} from "../../services/articl.service";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../../services/auth.service";
import {RecipeDto} from "../../dto/recipe-dto";
import {RecipeService} from "../../services/recipe.service";
import {CommentService} from "../../services/comment.service";
import {CommentDto} from "../../dto/comment-dto";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent  implements OnInit {
  selectedArticle: ArticleDto | null = null;
  selectedRecipe:RecipeDto | null=null;
  userRole: string = '';

  commentaire: string = '';
  isLoggedIn: boolean = false;
  constructor(
    private route: ActivatedRoute,
    private articleService: ArticlService,
    private authService :AuthService,
    private recipeService:RecipeService,
    private commentService: CommentService,
    private router: Router
  ) {}

  ngOnInit() {
    this.isLoggedIn = this.authService.isLoggedIn();
    this.route.paramMap.subscribe(params => {
      const articleId = params.get('idarticle');
      const recipeId =params.get('idrecipe');
      if (articleId) {
        this.articleService.getArticleById(+articleId).subscribe((article: ArticleDto) => {
          this.selectedArticle = article; // Save the selected article details
        });
      }else if(recipeId){
        this.recipeService.getRecipeById(+recipeId).subscribe((recipe:RecipeDto)=>{
          this.selectedRecipe=recipe;
        })
      }
    });
    this.userRole = this.authService.getUserRole();
    console.log('User Role:', this.userRole);
  }

  addComment() {
    if (!this.isLoggedIn) {
      console.log('User is not authenticated. Redirecting to login page.');
      this.router.navigate(['/login']);
      return;
    }

    const visiteurIdString = this.authService.getCurrentUserId();
    if (visiteurIdString === null) {
      console.error('User ID is null despite being logged in');
      this.authService.logout();
      this.router.navigate(['/login']);
      return;
    }

    const visiteurId = Number(visiteurIdString);
    if (isNaN(visiteurId)) {
      console.error('Invalid user ID');
      this.authService.logout();
      this.router.navigate(['/login']);
      return;
    }

    const commentDto: CommentDto = { content: this.commentaire };

    this.commentService.addComment(visiteurId, commentDto).subscribe(
      (response) => {
        console.log('Commentaire ajouté:', response);
        this.commentaire = ''; // Reset the comment field
      },
      (error) => {
        console.error('Erreur lors de l\'ajout du commentaire:', error);
        // Handle error (show message to user, etc.)
      }
    );
  }

}
