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


const routes: Routes = [

  {path: 'login', component: LoginComponent},
  {path: 'add', component: DashboardspecialistComponent},
  {path: 'article', component: AddArticlComponent},
  {path: 'main', component: MainComponent},
  {path: 'recipes', component: AddrecipeComponent},
  {path: 'footer', component: FooterComponent},
  {path: 'artistic', component: ArticlListComponent},
  {path: 'register', component: RegisterComponent},
  { path: '', redirectTo: 'main', pathMatch: 'full' },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
