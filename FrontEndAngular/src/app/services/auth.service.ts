import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RegisterUserDto} from "../dto/register-user-dto";
import {BehaviorSubject, Observable, of, tap} from "rxjs";
import {Loginuserdto} from "../dto/loginuserdto";
import {Loginresponce} from "../models/loginresponce";
import {jwtDecode} from "jwt-decode";
import {JwtHelperService} from "@auth0/angular-jwt";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8081/api/auth';
  private isAuthenticatedSubject = new BehaviorSubject<boolean>(false);

  constructor(
    private http: HttpClient,
    private jwtHelper:JwtHelperService
  ) {
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
  // login(loginUser: Loginuserdto): Observable<any> {
  //   return this.http.post<any>(`${this.apiUrl}/login`, loginUser).pipe(
  //     tap(response => {
  //       if (response && response.token) {
  //         localStorage.setItem('token', response.token);
  //         localStorage.setItem('currentUser', JSON.stringify(response.user));
  //         this.isAuthenticatedSubject.next(true);
  //       }
  //     })
  //   );
  // }
  login(loginUser: Loginuserdto): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/login`, loginUser).pipe(
      tap(response => {
        if (response && response.token) {
          localStorage.setItem('token', response.token);
          if (response.user) {
            localStorage.setItem('currentUser', JSON.stringify(response.user));
          } else {
            console.error('Login response does not contain user data:', response);
          }
          this.isAuthenticatedSubject.next(true);
        } else {
          console.error('Login response does not contain a token:', response);
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
  // getCurrentUserId(): number | null {
  //   const user = JSON.parse(localStorage.getItem('currentUser') || '{}');
  //   return user?.id || null;
  // }
  // getCurrentUserId(): number {
  //   const user = JSON.parse(localStorage.getItem('currentUser') || '{}');
  //   return user?.id ?? 0; // Default to 0 if user.id is null
  // }
  getCurrentUserId(): string | null {
    const token = localStorage.getItem('token');
    const userDataString = localStorage.getItem('currentUser');

    console.log('Token:', token);
    console.log('User data string:', userDataString);

    if (!token) {
      console.error('No token found in localStorage');
      return null;
    }

    if (!userDataString) {
      console.error('No user data found in localStorage');
      return null;
    }

    try {
      const userData = JSON.parse(userDataString);
      if (!userData || !userData.id) {
        console.error('User data does not contain an id:', userData);
        return null;
      }
      return userData.id.toString();
    } catch (error) {
      console.error('Error parsing user data:', error);
      console.error('Raw user data string:', userDataString);
      return null;
    }
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

  isLoggedIn(): boolean {
    const token = localStorage.getItem('token');
    return !!token;
  }




  getId():number | null
  {
  const token = localStorage.getItem("token");
  if(token)
  {
    const  decodedToken = this.jwtHelper.decodeToken(token);
    return decodedToken.id;
  }
  return null;
  }



}
