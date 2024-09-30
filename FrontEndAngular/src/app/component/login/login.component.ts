import { Component } from '@angular/core';

import {jwtDecode} from 'jwt-decode';
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {Loginuserdto} from "../../dto/loginuserdto";
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  // userName: string = '';
  // password: string = '';
  //
  // constructor(private authService: AuthService, private router: Router) {}
  //
  // login() {
  //   const loginUser: Loginuserdto = new Loginuserdto(this.userName, this.password);
  //   this.authService.login(loginUser).subscribe({
  //     next: (response) => {
  //       console.log('Login successful:', response);
  //       localStorage.setItem('token', response.token);
  //       console.log('Token expires in:', response.expiresIn);
  //
  //       const token = response.token;
  //       const decodedToken: any = jwtDecode(token);
  //
  //       if (decodedToken.role === 'ADMIN') {
  //         this.router.navigateByUrl('/dashboard');
  //       } else if (decodedToken.role === 'VISITEUR') {
  //         this.router.navigateByUrl('/visitor-dashboard');
  //       }else if(decodedToken.role === 'SPECIALIST') {
  //         this.router.navigateByUrl('/add');
  //       }
  //     },
  //     error: (err) => {
  //       console.error('Login failed:', err);
  //     },
  //     complete: () => {
  //       console.log('Login process complete.');
  //     }
  //   });
  // }
  //
  // openSignUp() {
  //   this.router.navigate(['/register']);
  // }

  userName: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  login() {
    const loginUser: Loginuserdto = new Loginuserdto(this.userName, this.password);
    this.authService.login(loginUser).subscribe({
      next: (response) => {
        console.log('Login successful:', response);
        console.log('Token expires in:', response.expiresIn);

        const token = response.token;
        const decodedToken: any = jwtDecode(token);

        if (decodedToken.role === 'ADMIN') {
          this.router.navigateByUrl('/dashboard');
        } else if (decodedToken.role === 'VISITEUR') {
          this.router.navigateByUrl('/visitor-dashboard');
        } else if (decodedToken.role === 'SPECIALIST') {
          this.router.navigateByUrl('/add');
        }
      },
      error: (err) => {
        console.error('Login failed:', err);
      },
      complete: () => {
        console.log('Login process complete.');
      }
    });
  }

  openSignUp() {
    this.router.navigate(['/register']);
  }
}

