import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {EmailFormComponentComponent} from "./component/email-form-component/email-form-component.component";
import {LoginComponent} from "./component/login/login.component";
import {DashboardspecialistComponent} from "./component/dashboardspecialist/dashboardspecialist.component";
import {AddArticlComponent} from "./component/add-articl/add-articl.component";


const routes: Routes = [

  {path: 'login', component: LoginComponent},
  {path: 'add', component: DashboardspecialistComponent},
  {path: 'article', component: AddArticlComponent},
  { path: '', redirectTo: 'login', pathMatch: 'full' },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
