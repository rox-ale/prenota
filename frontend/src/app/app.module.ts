import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { LoginComponent } from './login';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { JwtInterceptor, ErrorInterceptor } from './_helpers';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { StartComponent } from './start/start.component';

import { CommonModule } from '@angular/common';
 
import { ForbiddenComponent } from './forbidden/forbidden.component';  
import { AdminModule } from './adminModule/admin.module';
import { UserModule } from './userModule/user.module';
@NgModule({
  declarations: [
    AppComponent,

    LoginComponent,
    StartComponent,
 
    ForbiddenComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    ReactiveFormsModule, 
    FormsModule,
    CommonModule,
    AdminModule,
    UserModule
  ],
  providers: [        { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true }//,
],
  bootstrap: [AppComponent]
})
export class AppModule { }
