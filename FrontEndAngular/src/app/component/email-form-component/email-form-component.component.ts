import { Component } from '@angular/core';
import {EmailService} from "../../services/email-service.service";

@Component({
  selector: 'app-email-form-component',
  templateUrl: './email-form-component.component.html',
  styleUrls: ['./email-form-component.component.css']
})
export class EmailFormComponentComponent {
  to: string = '';
  subject: string = '';
  body: string = '';

  constructor(private emailService: EmailService) {}

  onSubmit() {
    const emailData = { to: this.to, subject: this.subject, body: this.body };
    this.emailService.sendEmail(emailData).subscribe(
      response => console.log('Email sent successfully', response),
      error => console.error('Error sending email', error)
    );
  }
}
