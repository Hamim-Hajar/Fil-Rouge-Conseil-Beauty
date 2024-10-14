import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-headertwo',
  templateUrl: './headertwo.component.html',
  styleUrls: ['./headertwo.component.css']
})
export class HeadertwoComponent implements OnInit {
  isAuthenticated$ = this.authService.isAuthenticated();

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit() {}

  logout() {
    this.authService.logout();
    this.router.navigateByUrl('/main');
  }
}
