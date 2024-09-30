import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RegisterUserDto} from "../dto/register-user-dto";
import {BehaviorSubject, Observable, of, tap} from "rxjs";
import {Loginuserdto} from "../dto/loginuserdto";
import {Loginresponce} from "../models/loginresponce";

@Injectable({
  providedIn: 'root'
})
export class AuthService {


  private apiUrl = 'http://localhost:8081/api/auth';

  private isAuthenticatedSubject = new BehaviorSubject<boolean>(false);

  // URL de l'API d'authentification

  constructor(private http: HttpClient) {
    this.checkAuthStatus();
  }
  checkAuthStatus() {
    const token = localStorage.getItem('token');
    this.isAuthenticatedSubject.next(!!token);
  }

  isAuthenticated(): Observable<boolean> {
    return this.isAuthenticatedSubject.asObservable();
  }
  signup(registerUserDto: RegisterUserDto): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/signup`, registerUserDto);
 }


  // login(loginUser: Loginuserdto):Observable<any>{
  //   return this.http.post<any>(`${this.apiUrl}/login`, loginUser);
  //
  // }
  login(loginUser: Loginuserdto): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/login`, loginUser).pipe(
      tap(response => {
        if (response && response.token) {
          localStorage.setItem('token', response.token);
          localStorage.setItem('currentUser', JSON.stringify(response.user));
          this.isAuthenticatedSubject.next(true);
        }
      })
    );
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('currentUser');
    this.isAuthenticatedSubject.next(false);
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
