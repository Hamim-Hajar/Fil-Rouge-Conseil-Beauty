import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {EmailFormComponentComponent} from "./component/email-form-component/email-form-component.component";
import {LoginComponent} from "./component/login/login.component";
import {DashboardspecialistComponent} from "./component/dashboardspecialist/dashboardspecialist.component";
import {AddArticlComponent} from "./component/add-articl/add-articl.component";
import {MainComponent} from "./component/main/main.component";
import {FooterComponent} from "./component/footer/footer.component";
import {ArticlListComponent} from "./component/articl-list/articl-list.component";
import {RegisterComponent} from "./component/register/register.component";
import {AddrecipeComponent} from "./component/addrecipe/addrecipe.component";
import {RecipeListComponent} from "./component/recipe-list/recipe-list.component";
import {HomeComponent} from "./component/home/home.component";
import {VisiteurDashboardComponent} from "./component/visiteur-dashboard/visiteur-dashboard.component";
import {LeftSidebarComponent} from "./component/header/left-sidebar.component";
import {HeadertwoComponent} from "./component/headertwo/headertwo.component";
import {UpdatearticleComponent} from "./component/updatearticle/updatearticle.component";
import {HeaderthreeComponent} from "./component/headerthree/headerthree.component";
import {UpdaterecipeComponent} from "./component/updaterecipe/updaterecipe.component";


const routes: Routes = [

  {path: 'login', component: LoginComponent},
  {path: 'add', component: DashboardspecialistComponent},
  {path: 'article', component: AddArticlComponent},
  {path: 'main', component: MainComponent},
  {path: 'recipes', component: AddrecipeComponent},
  {path: 'recipe-list', component: RecipeListComponent},
  {path: 'footer', component: FooterComponent},
  {path: 'artistic', component: ArticlListComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'email', component: EmailFormComponentComponent},
  {path: 'home', component: HomeComponent},
  {path: 'header', component: LeftSidebarComponent},
  {path: 'header-two', component: HeadertwoComponent},
  {path: 'header-three', component: HeaderthreeComponent},
  {path: 'visitor-dashboard', component: VisiteurDashboardComponent},
  {path:'updaterecipe/:id',component:UpdaterecipeComponent},
  {path: 'update/:id', component: UpdatearticleComponent},
  { path: '', redirectTo: 'main', pathMatch: 'full' },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
