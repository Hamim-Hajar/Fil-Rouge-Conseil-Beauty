import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import {RouterModule, RouterOutlet} from '@angular/router';

// Components
import { AppComponent } from './app.component';
import { EmailFormComponentComponent } from './component/email-form-component/email-form-component.component';
import { AddArticlComponent } from './component/add-articl/add-articl.component';
import { LoginComponent } from './component/login/login.component';

import { DashboardspecialistComponent } from './component/dashboardspecialist/dashboardspecialist.component';

// Services & Interceptors
import { JwtService } from './services/jwt.service';

// Routing Module
import { AppRoutingModule } from "./app-routing.module";
import { Interciptor} from "./interceptors/auth-interseptor.service";

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {MatMenuModule} from "@angular/material/menu";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatListModule} from "@angular/material/list";
import { LeftSidebarComponent } from './component/header/left-sidebar.component';
import {CommonModule} from "@angular/common";
import { MainComponent } from './component/main/main.component';
import { FooterComponent } from './component/footer/footer.component';
import { ArticlListComponent } from './component/articl-list/articl-list.component';
import { RegisterComponent } from './component/register/register.component';
import {MatTableModule} from "@angular/material/table";
import { AddrecipeComponent } from './component/addrecipe/addrecipe.component';
import { RecipeListComponent } from './component/recipe-list/recipe-list.component';
import { HomeComponent } from './component/home/home.component';
import { VisiteurDashboardComponent } from './component/visiteur-dashboard/visiteur-dashboard.component';
import { HeadertwoComponent } from './component/headertwo/headertwo.component';
import { UpdatearticleComponent } from './component/updatearticle/updatearticle.component';
import { HeaderthreeComponent } from './component/headerthree/headerthree.component';


import {JwtModule} from "@auth0/angular-jwt";
import { UpdaterecipeComponent } from './component/updaterecipe/updaterecipe.component';
import { ConfirmDialogComponent } from './component/confirm-dialog/confirm-dialog.component';
import {MatDialogModule} from "@angular/material/dialog";
import { AdmindashboardComponent } from './admindashboard/admindashboard.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule} from "@angular/material/select";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatSortModule} from "@angular/material/sort";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatInputModule} from "@angular/material/input";
import {MatNativeDateModule} from "@angular/material/core";
import { FavoritesComponent } from './favorites/favorites.component';

@NgModule({
  declarations: [
    AppComponent,
    EmailFormComponentComponent,
    AddArticlComponent,
    LoginComponent,

    DashboardspecialistComponent,
    LeftSidebarComponent,
    MainComponent,
    FooterComponent,
    ArticlListComponent,
    RegisterComponent,
    AddrecipeComponent,
    RecipeListComponent,
    HomeComponent,
    VisiteurDashboardComponent,
    HeadertwoComponent,
    UpdatearticleComponent,
    HeaderthreeComponent,
    UpdaterecipeComponent,
    ConfirmDialogComponent,
    AdmindashboardComponent,
    FavoritesComponent,



  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterOutlet,
    AppRoutingModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatMenuModule,
    MatSidenavModule,
    MatListModule, RouterModule, CommonModule, MatTableModule, MatDialogModule,
    MatNativeDateModule, MatPaginatorModule,


    JwtModule.forRoot(
      {
        config: {
          tokenGetter: () => localStorage.getItem('token'),
          allowedDomains: ['http://localhost:8081/login']
        }
      }
    ), MatFormFieldModule, MatSelectModule, MatDatepickerModule, MatSortModule, MatPaginatorModule, MatInputModule


  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: Interciptor,
      multi: true}


  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
