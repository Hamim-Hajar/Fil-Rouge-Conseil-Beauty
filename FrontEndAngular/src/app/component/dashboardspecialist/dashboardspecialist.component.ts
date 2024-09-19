import {AfterViewInit, Component, ComponentFactoryResolver, OnInit, ViewChild, ViewContainerRef} from '@angular/core';
import { Router } from "@angular/router";
import { AddArticlComponent } from "../add-articl/add-articl.component"; // Assurez-vous que le chemin d'importation est correct

@Component({
  selector: 'app-dashboardspecialist',
  templateUrl: './dashboardspecialist.component.html',
  styleUrls: ['./dashboardspecialist.component.css']
})
export class DashboardspecialistComponent implements AfterViewInit {
  @ViewChild('componentContainer', { read: ViewContainerRef }) componentContainer!: ViewContainerRef;

  constructor(
    private router: Router
  ) {}

  ngAfterViewInit(): void {
    // Le ViewChild est garanti d'être initialisé ici
  }

  logout(): void {
    localStorage.removeItem("token");
    this.router.navigateByUrl('/login');
  }

  loadComponent(componentName: string): void {
    if (!this.componentContainer) return;

    this.componentContainer.clear();

    if (componentName === 'article') {
      this.componentContainer.createComponent(AddArticlComponent);
    }
    // Vous pouvez ajouter d'autres conditions ici pour charger d'autres composants
  }
}
