import { Component } from '@angular/core';
import {RegisterUserDto} from "../../dto/register-user-dto";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerForm: FormGroup;
  loading = false;
  error: string | null = null;

  constructor(private fb: FormBuilder, private authService: AuthService) {
    this.registerForm = this.fb.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      role: ['', Validators.required] // Le rôle peut être choisi par l'utilisateur (VISITEUR, SPECIALIST, ADMIN)
    });
  }

  onSubmit() {
    if (this.registerForm.valid) {
      this.loading = true;
      const { username, email, password, role } = this.registerForm.value;

      const registerDto = new RegisterUserDto(username, email, password, role);

      this.authService.signup(registerDto).subscribe({
        next: (response) => {
          console.log('User registered successfully', response);
          this.loading = false;
          // Rediriger ou afficher un message de succès
        },
        error: (err) => {
          console.error('Registration failed', err);
          this.error = 'Registration failed. Please try again.';
          this.loading = false;
        }
      });
    }
  }

}
