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
  signupForm: FormGroup;
  userRoles = Object.values(Userrole);

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.signupForm = this.formBuilder.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      role: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.signupForm.valid) {
      const registerDto: RegisterUserDto = {
        username: this.signupForm.get('username')?.value,
        email: this.signupForm.get('email')?.value,
        password: this.signupForm.get('password')?.value,
        role: this.signupForm.get('role')?.value as Userrole
      };

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

}
