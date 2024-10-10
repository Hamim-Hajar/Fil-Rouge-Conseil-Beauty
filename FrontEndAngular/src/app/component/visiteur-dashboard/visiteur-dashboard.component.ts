import {AfterViewInit, Component, OnInit, ViewChild, ViewContainerRef} from '@angular/core';
import {Router} from "@angular/router";
import {AddArticlComponent} from "../add-articl/add-articl.component";
import {ArticlListComponent} from "../articl-list/articl-list.component";
import {AddrecipeComponent} from "../addrecipe/addrecipe.component";
import {RecipeListComponent} from "../recipe-list/recipe-list.component";
import {EmailFormComponentComponent} from "../email-form-component/email-form-component.component";
import {jwtDecode} from "jwt-decode";

@Component({
  selector: 'app-visiteur-dashboard',
  templateUrl: './visiteur-dashboard.component.html',
  styleUrls: ['./visiteur-dashboard.component.css']
})
export class VisiteurDashboardComponent implements AfterViewInit , OnInit {

  @ViewChild('componentContainer', { read: ViewContainerRef }) componentContainer!: ViewContainerRef;
  name : string =''
  constructor(
    private router: Router
  ) {}
  ngOnInit(): void {

    this.getName()
    console.log(this.name);

  }
  getName(){
    const value :any= localStorage.getItem('token')

    const decodeJwt : any = jwtDecode(value)

    this.name = decodeJwt.sub
    console.log(decodeJwt);

  }
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
