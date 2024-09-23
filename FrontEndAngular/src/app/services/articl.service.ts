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
  addArticle(articleDto: ArticleDto,specialistId : number , image :File): Observable<ArticleDto> {


    const formData = new FormData();
    formData.append('titre', articleDto.titre);
    formData.append('contenu', articleDto.contenu);
    formData.append('specialistId', specialistId.toString());
    formData.append('image', image);

    return this.http.post<ArticleDto>(`${this.apiUrl}/add`, formData,);
  }


  // Get all articles
  getAllArticles(): Observable<ArticleDto[]> {
    return this.http.get<ArticleDto[]>(`${this.apiUrl}/get_all_article`);
  }

  // Get an article by ID
  getArticleById(id: number): Observable<ArticleDto> {
    return this.http.get<ArticleDto>(`${this.apiUrl}/${id}`);
  }

  // Delete an article by ID
  deleteArticle(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }
}
