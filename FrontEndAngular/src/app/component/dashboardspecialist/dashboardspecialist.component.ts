import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-dashboardspecialist',
  templateUrl: './dashboardspecialist.component.html',
  styleUrls: ['./dashboardspecialist.component.css']
})
export class DashboardspecialistComponent implements OnInit {


  constructor(private router: Router) {
  }

  check = false

  logout(check: boolean) {

    if (check) {
      localStorage.removeItem("token")
      this.router.navigateByUrl('/login')
    }

  }

  ngOnInit(): void {
  }
}
