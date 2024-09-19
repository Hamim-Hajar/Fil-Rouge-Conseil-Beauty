import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ImageUploadService {
  private uploadUrl = 'http://localhost:8080/api/upload-image'; // Endpoint de ton API

  constructor(private http: HttpClient) { }

  uploadImage(image: File): Observable<string> {
    const formData = new FormData();
    formData.append('image', image);

    return this.http.post<string>('/api/upload-image', formData); // L'API renvoie l'URL de l'image
  }
}
