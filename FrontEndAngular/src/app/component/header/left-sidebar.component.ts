import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-left-sidebar',
  templateUrl: './left-sidebar.component.html',
  styleUrls: ['./left-sidebar.component.css']
})
export class LeftSidebarComponent implements OnInit {
  isAuthenticated$ = this.authService.isAuthenticated();
  isMainPage: boolean = false;
  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit() { this.router.events.subscribe(() => {
    this.isMainPage = this.router.url === '/main';
  });}

  logout() {
    this.authService.logout();
    this.router.navigateByUrl('/main');
  }
}
