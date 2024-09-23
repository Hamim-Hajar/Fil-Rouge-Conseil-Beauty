import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RegisterUserDto} from "../dto/register-user-dto";
import {Observable, of} from "rxjs";
import {Loginuserdto} from "../dto/loginuserdto";
import {Loginresponce} from "../models/loginresponce";

@Injectable({
  providedIn: 'root'
})
export class AuthService {


  private apiUrl = 'http://localhost:8080/api/auth';


  // URL de l'API d'authentification

  constructor(private http: HttpClient) { }

  signup(registerUserDto: RegisterUserDto): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/signup`, registerUserDto);
 }


  login(loginUser: Loginuserdto):Observable<any>{
    return this.http.post<any>(`${this.apiUrl}/login`, loginUser);
  }
  getCurrentUserId(): number | null {
    const user = JSON.parse(localStorage.getItem('currentUser') || '{}');
    return user?.id ;
  }
  getCurrentUser(): Observable<any> {
    const user = JSON.parse(localStorage.getItem('currentUser') || '{}');
    return of(
      user);
  }
}
