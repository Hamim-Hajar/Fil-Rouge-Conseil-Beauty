import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ArticleDto} from "../dto/article-dto";
import {Observable} from "rxjs";
import {AuthService} from "./auth.service";

@Injectable({
  providedIn: 'root'
})
export class ArticlService {
  private apiUrl = 'http://localhost:8080/api/articles'; // Backend API endpoint

  constructor(private http: HttpClient) {}

  // Add a new article
  addArticle(article: ArticleDto,specialist_id : number): Observable<ArticleDto> {
    return this.http.post<ArticleDto>(`${this.apiUrl}/add?specialist_id=${specialist_id}`, article);
  }


  // Get all articles
  getAllArticles(): Observable<ArticleDto[]> {
    return this.http.get<ArticleDto[]>(this.apiUrl);
  }

  // Get an article by ID
  getArticleById(id: number): Observable<ArticleDto> {
    return this.http.get<ArticleDto>(`${this.apiUrl}/${id}`);
  }

  // Delete an article by ID
  deleteArticle(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
