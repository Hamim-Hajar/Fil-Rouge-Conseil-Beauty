import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CommentDto} from "../dto/comment-dto";

@Injectable({
  providedIn: 'root'
})
export class CommentService {


  private baseUrl = 'http://localhost:8080/api/comments'; // Remplacez par votre URL backend

  constructor(private http: HttpClient) { }
  addComment(recipeId: number, userId: number, content: string): Observable<CommentDto> {
    const url = `${this.baseUrl}/add?recipeId=${recipeId}&userId=${userId}`;
    return this.http.post<CommentDto>(url, content);
  }

  getCommentsByRecipe(recipeId: number): Observable<CommentDto[]> {
    return this.http.get<CommentDto[]>(`${this.baseUrl}/recipe/${recipeId}`);
  }

  // Mettre à jour un commentaire
  updateComment(commentId: number, newContent: string): Observable<CommentDto> {
    return this.http.put<CommentDto>(`${this.baseUrl}/${commentId}`, newContent);
  }

  // Supprimer un commentaire
  deleteComment(commentId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${commentId}`);
  }
}
