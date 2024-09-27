import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class FavrecipeService {

  private apiUrl = 'http://localhost:8081/api';

  constructor(private http: HttpClient) {}

  getRecipe(id: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/recipes/${id}`);
  }

  addRating(recipeId: number, visiteurId: number, value: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/ratings`, { recipeId, visiteurId, value });
  }

  getAverageRating(recipeId: number): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/ratings/average/${recipeId}`);
  }

}
