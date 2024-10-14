import { Component, OnInit } from '@angular/core';
import { ArticleDto } from "../../dto/article-dto";
import { ArticlService } from "../../services/articl.service";
import { ActivatedRoute, Router } from "@angular/router";
import { AuthService } from "../../services/auth.service";
import { RecipeDto } from "../../dto/recipe-dto";
import { RecipeService } from "../../services/recipe.service";
import { CommentService } from "../../services/comment.service";
import { CommentDto } from "../../dto/comment-dto";
import {jwtDecode} from "jwt-decode";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  selectedArticle: ArticleDto | null = null;
  selectedRecipe: RecipeDto | null = null;
  userRole: string = '';

  commentaire: string = '';
  comments: CommentDto[] = [];
  isLoggedIn: boolean = false;

  name : string =''

  getName(){
    const value :any= localStorage.getItem('token')

    const decodeJwt : any = jwtDecode(value)

    this.name = decodeJwt.sub
    console.log(decodeJwt);

  }

  constructor(
    private route: ActivatedRoute,
    private articleService: ArticlService,
    private authService: AuthService,
    private recipeService: RecipeService,
    private commentService: CommentService,
    private router: Router
  ) {}

  ngOnInit() {
    this.isLoggedIn = this.authService.isLoggedIn();
    console.log('Is user logged in?', this.isLoggedIn);
    console.log("the id of user is ",this.getId())

    this.route.paramMap.subscribe(params => {
      const articleId = params.get('idarticle');
      const recipeId = params.get('idrecipe');
      console.log("The recipe ID is:", recipeId);

      if (articleId) {
        this.articleService.getArticleById(+articleId).subscribe((article: ArticleDto) => {
          this.selectedArticle = article;
        });
      } else if (recipeId) {
        this.recipeService.getRecipeById(+recipeId).subscribe((recipe: RecipeDto) => {
          this.selectedRecipe = recipe;
          this.fetchComments();
        });
      }
      this.getName()
      console.log(this.name);
    });

    this.userRole = this.authService.getUserRole();
    console.log('User Role:', this.userRole);
  }
  fetchComments() {
    if (this.selectedRecipe) {
      this.commentService.getCommentsByRecipe(this.selectedRecipe.id).subscribe(
        (comments: CommentDto[]) => {
          console.log('Comments fetched for recipe:', comments);
          this.comments = comments;
        },
        (error) => {
          console.error('Error fetching comments for recipe:', error);
        }
      );
    }
  }

  // addComment() {
  //   console.log('Adding comment...');
  //   if (!this.isLoggedIn) {
  //     console.log('User is not authenticated. Redirecting to login page.');
  //     this.router.navigate(['/login']);
  //     return;
  //   }
  //
  //   const visiteurId = this.getId();
  //   console.log('Visitor ID:', visiteurId);
  //
  //   if (visiteurId === null) {
  //     console.error('User ID is null despite being logged in');
  //     this.authService.logout();
  //     this.router.navigate(['/login']);
  //     return;
  //   }
  //
  //   const commentDto: CommentDto = {
  //     content: this.commentaire,
  //     recipe_id: this.selectedRecipe?.id
  //   };
  //
  //   console.log('Sending comment:', commentDto, 'for user ID:', visiteurId);
  //   this.commentService.addComment(visiteurId, commentDto).subscribe(
  //     (response) => {
  //       console.log('Commentaire ajouté:', response);
  //       this.commentaire = '';
  //
  //       this.comments.push(response);
  //     },
  //     (error) => {
  //       console.error('Erreur lors de l\'ajout du commentaire:', error);
  //     }
  //   );
  // }
  addComment() {
    console.log('Adding comment...');
    if (!this.isLoggedIn) {
      console.log('User is not authenticated. Redirecting to login page.');
      this.router.navigate(['/login']);
      return;
    }

    const visiteurId = this.getId();
    console.log('Visitor ID:', visiteurId);

    if (visiteurId === null) {
      console.error('User ID is null despite being logged in');
      this.authService.logout();
      this.router.navigate(['/login']);
      return;
    }

    const commentDto: CommentDto = {
      content: this.commentaire,
      recipe_id: this.selectedRecipe?.id
    };

    console.log('Sending comment:', commentDto, 'for user ID:', visiteurId);

    this.commentService.addComment(visiteurId, commentDto).subscribe(
      () => {
        console.log('Comment successfully added.');

        // Manually create the new comment object
        const newComment: CommentDto = {
          content: this.commentaire,
          recipe_id: this.selectedRecipe?.id,
          timestamp: new Date().toISOString() ,

        };

        // Push the new comment to the comments list
        this.comments.push(newComment);

        // Clear the textarea after adding
        this.commentaire = '';
      },
      (error) => {
        console.error('Error adding comment:', error);
      }
    );
  }

  getId(): number | null {
    return this.authService.getId();
  }
}
