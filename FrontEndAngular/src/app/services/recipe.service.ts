import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Recipe} from "../models/recipe";
import {catchError, Observable, throwError} from "rxjs";
import {RecipeCategory} from "../enums/recipe-category";
import {RecipeDto} from "../dto/recipe-dto";
import {ArticleDto} from "../dto/article-dto";

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  private apiUrl = 'http://localhost:8081/api/recipe'; // URL of your Spring Boot API

  constructor(private http: HttpClient) {}

  // Add a new recipe
  addRecipe(recipeDto:RecipeDto,specialist_id:number,image:File): Observable<RecipeDto> {
   const formData=new FormData();
   formData.append('name',recipeDto.name);
    formData.append('description',recipeDto.description);
    formData.append('ingredients',recipeDto.ingredients);
    formData.append('instructions',recipeDto.instructions);
    formData.append('specialist_id', specialist_id.toString());
    formData.append('category',recipeDto.category);
    formData.append('image', image);
    return this.http.post<RecipeDto>(`${this.apiUrl}/add`, formData,);
  }


  // Get recipe by ID
  getRecipeById(id: number): Observable<RecipeDto> {
    return this.http.get<RecipeDto>(`${this.apiUrl}/get/${id}`);

  }

  // Get all recipes

  getAllRecipes(): Observable<RecipeDto[]> {
    return this.http.get<RecipeDto[]>(`${this.apiUrl}/all`); // Le typage doit être RecipeDto[]
  }


  // Update a recipe
  updateRecipe( recipeData: FormData,id: number): Observable<any> {
    const url = `${this.apiUrl}/update/${id}`;
  return this.http.put(url,recipeData);

  }


  // Delete a recipe
  deleteRecipe(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);

  }


  // // Get recipes by category
  // getRecipesByCategory(category: RecipeCategory): Observable<Recipe[]> {
  //   return this.http.get<Recipe[]>(`${this.apiUrl}/category/${category}`)
  //     .pipe(catchError(this.handleError));
  // }

  getRecipesByCategory(category: RecipeCategory): Observable<RecipeDto[]> {
    return this.http.get<RecipeDto[]>(`${this.apiUrl}/category/${category}`)
      .pipe(catchError(this.handleError));
  }
    // Handle errors from the API
  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // Client-side or network error
      console.error('An error occurred:', error.error.message);
    } else {
      // Backend error
      console.error(`Backend returned code ${error.status}, body was: ${error.error}`);
    }
    return throwError('Something went wrong; please try again later.');
  }
}
