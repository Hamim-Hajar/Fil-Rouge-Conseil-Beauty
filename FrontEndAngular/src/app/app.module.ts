import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { EmailFormComponentComponent } from './component/email-form-component/email-form-component.component';
import {FormsModule} from "@angular/forms";
import { AddArticlComponent } from './component/add-articl/add-articl.component';

@NgModule({
  declarations: [
    AppComponent,
    EmailFormComponentComponent,
    AddArticlComponent
  ],
    imports: [
        BrowserModule,
        FormsModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
