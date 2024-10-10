import { Component } from '@angular/core';
import {RegisterUserDto} from "../../dto/register-user-dto";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {Userrole} from "../../enums/userrole";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  signUpForm: FormGroup;
  userRoles = Object.values(Userrole);

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.signUpForm = this.formBuilder.group({
      userName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      role: ['', Validators.required]
    });
  }

  signUp() {
     const registerDto :RegisterUserDto = this.signUpForm.value
      this.authService.signup(registerDto).subscribe(
        (user) => {
          console.log('Signup successful:', user);
          this.router.navigate(['/login']);
        },
        (error) => {
          console.error('Signup failed:', error);
        }
      );
    }
  }


