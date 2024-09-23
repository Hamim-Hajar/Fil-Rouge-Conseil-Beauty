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
import { FavoritComponent } from './component/favorit/favorit.component';
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
import { LeftSidebarComponent } from './component/left-sidebar/left-sidebar.component';
import {CommonModule} from "@angular/common";
import { MainComponent } from './component/main/main.component';
import { FooterComponent } from './component/footer/footer.component';
import { ArticlListComponent } from './component/articl-list/articl-list.component';
import { RegisterComponent } from './component/register/register.component';
import {MatTableModule} from "@angular/material/table";
import { AddrecipeComponent } from './component/addrecipe/addrecipe.component';

@NgModule({
  declarations: [
    AppComponent,
    EmailFormComponentComponent,
    AddArticlComponent,
    LoginComponent,
    FavoritComponent,
    DashboardspecialistComponent,
    LeftSidebarComponent,
    MainComponent,
    FooterComponent,
    ArticlListComponent,
    RegisterComponent,
    AddrecipeComponent,


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
    MatListModule, RouterModule, CommonModule, MatTableModule,

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
