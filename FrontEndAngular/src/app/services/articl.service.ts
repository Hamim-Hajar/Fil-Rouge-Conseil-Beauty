import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ArticleDto} from "../dto/article-dto";
import {Observable} from "rxjs";
import {AuthService} from "./auth.service";

@Injectable({
  providedIn: 'root'
})
export class ArticlService {
  private apiUrl = 'http://localhost:8081/api/articles'; // Backend API endpoint

  constructor(private http: HttpClient) {}

  // Add a new article
  addArticle(articleDto: ArticleDto,specialist_id : number , image :File): Observable<ArticleDto> {


    const formData = new FormData();
    formData.append('titre', articleDto.titre);
    formData.append('contenu', articleDto.contenu);
    formData.append('specialist_id', specialist_id.toString());
    formData.append('image', image);

    return this.http.post<ArticleDto>(`${this.apiUrl}/add`, formData,);
  }
  // Get all articles
  getAllArticles(): Observable<ArticleDto[]> {
    return this.http.get<ArticleDto[]>(`${this.apiUrl}/get_all_article`);
  }

  // Get an article by ID
  getArticleById(id: number): Observable<ArticleDto> {
    return this.http.get<ArticleDto>(`${this.apiUrl}/get/${id}`);
  }

  // Delete an article by ID
  deleteArticle(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }

  // updateArticle(id: number, titre: string, contenu: string, image?: File): Observable<ArticleDto> {
  //   const formData: FormData = new FormData();
  //   formData.append('titre', titre);
  //   formData.append('contenu', contenu);
  //
  //   if (image) {
  //     formData.append('image', image);
  //   }
  //
  //   return this.http.put<ArticleDto>(`${this.apiUrl}/update/${id}`, formData, {
  //
  //   });
  // }
  updateArticle(articleData: FormData,id:number): Observable<any> {
    const url = `${this.apiUrl}/update/${id}`;
    return this.http.put(url, articleData);
  }

}
