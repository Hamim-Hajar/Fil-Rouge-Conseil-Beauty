import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RegisterUserDto} from "../dto/register-user-dto";
import {BehaviorSubject, Observable, of, tap} from "rxjs";
import {Loginuserdto} from "../dto/loginuserdto";
import {Loginresponce} from "../models/loginresponce";
import {jwtDecode} from "jwt-decode";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8081/api/auth';
  private isAuthenticatedSubject = new BehaviorSubject<boolean>(false);

  constructor(private http: HttpClient) {
    this.checkAuthStatus();
  }

  // Check authentication status by looking for the token
  checkAuthStatus() {
    const token = localStorage.getItem('token');
    this.isAuthenticatedSubject.next(!!token);
  }

  // Observable to return authentication status
  isAuthenticated(): Observable<boolean> {
    return this.isAuthenticatedSubject.asObservable();
  }

  // Signup function
  signup(registerUserDto: RegisterUserDto): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/signup`, registerUserDto);
  }

  // Login function
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


  // Logout function
  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('currentUser');
    this.isAuthenticatedSubject.next(false);
  }

  // Get current user ID
  getCurrentUserId(): number | null {
    const user = JSON.parse(localStorage.getItem('currentUser') || '{}');
    return user?.id || null;
  }

  // Get current user
  getCurrentUser(): Observable<any> {
    const user = JSON.parse(localStorage.getItem('currentUser') || '{}');
    return of(user);
  }

  // Get the current user's role (visitor or specialist)
  getUserRole(): string {
    const token = localStorage.getItem('token');

    if (token) {
      const decodedToken: any = jwtDecode(token);
      return decodedToken.role || '';  // Return the role from the decoded token
    }

    return '';  // Return empty string if no token is found
  }
}
