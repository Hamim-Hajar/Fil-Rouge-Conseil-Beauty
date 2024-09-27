import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EmailService {
  private apiUrl = 'http://localhost:8081/api/email';

  constructor(private http: HttpClient) { }

  sendEmail(emailData: any) {
    return this.http.post(this.apiUrl, emailData);
  }
}
