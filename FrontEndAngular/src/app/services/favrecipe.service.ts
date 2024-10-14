import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {RecipeDto} from "../dto/recipe-dto";

@Injectable({
  providedIn: 'root'
})
export class FavrecipeService {

  private apiUrl = 'http://localhost:8081/api/favorites';
  constructor(private http: HttpClient) {}

  addFavorite(visiteurId: number, recipeId: number): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/${visiteurId}/${recipeId}`, {});
  }

  removeFavorite(visiteurId: number, recipeId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${visiteurId}/${recipeId}`);
  }

  getFavorites(visiteurId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/${visiteurId}`);
  }

}
