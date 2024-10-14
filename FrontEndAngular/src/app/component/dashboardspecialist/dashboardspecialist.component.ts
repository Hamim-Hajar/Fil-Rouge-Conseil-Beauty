import {AfterViewInit, Component, ComponentFactoryResolver, OnInit, ViewChild, ViewContainerRef} from '@angular/core';
import { Router } from "@angular/router";
import { AddArticlComponent } from "../add-articl/add-articl.component";
import {ArticlListComponent} from "../articl-list/articl-list.component";
import {AddrecipeComponent} from "../addrecipe/addrecipe.component";
import {RecipeListComponent} from "../recipe-list/recipe-list.component";
import {EmailFormComponentComponent} from "../email-form-component/email-form-component.component"; // Assurez-vous que le chemin d'importation est correct

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
    this.router.navigateByUrl('/main');
  }

  loadComponent(componentName: string): void {
    if (!this.componentContainer) return;

    this.componentContainer.clear();

    if (componentName === 'article') {
      this.componentContainer.createComponent(AddArticlComponent);
    }
    if (componentName === 'artistic') {
      this.componentContainer.createComponent(ArticlListComponent);
    }
    if (componentName === 'recipes') {
      this.componentContainer.createComponent(AddrecipeComponent);
    }
    if (componentName === 'recipe-list') {
      this.componentContainer.createComponent(RecipeListComponent);
    }
    if (componentName === 'email') {
      this.componentContainer.createComponent(EmailFormComponentComponent);
    }

  }
}
