import { Injectable } from '@angular/core';
import {jwtDecode }from 'jwt-decode'; // Import jwt-decode library

@Injectable({
  providedIn: 'root'
})
export class JwtService {

  private tokenKey = 'auth-token';

  // Store the token in local storage
  storeToken(token: string): void {
    localStorage.setItem(this.tokenKey, token);
  }

  // Retrieve the token from local storage
  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  // Remove the token from local storage
  removeToken(): void {
    localStorage.removeItem(this.tokenKey);
  }

  // Decode the token to get its payload
  decodeToken(): any {
    const token = this.getToken();
    if (token) {
      return jwtDecode(token);  // Decode the JWT token
    }
    return null;
  }

  // Get the user role from the decoded token
  getUserRole(): string | null {
    const decodedToken = this.decodeToken();
    if (decodedToken && decodedToken.role) {
      return decodedToken.role;  // Assuming the role is stored under 'role'
    }
    return null;
  }
}
