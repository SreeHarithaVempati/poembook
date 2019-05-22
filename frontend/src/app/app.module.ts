import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PoemsModule } from './modules/poems/poems.module';
import { DashboardComponent } from './modules/poems/dashboard/dashboard.component';
import { PoemService } from './modules/poems/poem.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FavouriteComponent } from './modules/poems/favourite/favourite.component';
import { Routes, RouterModule } from '@angular/router';
import { AuthenticationModule } from './modules/authentication/authentication.module';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AuthenticationService } from './modules/authentication/authentication.service';
import { AuthguardService } from './authguard.service';
import { TokenInterceptor } from './modules/poems/interceptor.service';
import {SnackbarModule} from 'ngx-snackbar';

const appRoutes: Routes=[{
  path:'',
  redirectTo:'/dashboard',
  pathMatch:'full',
}]
@NgModule({
  declarations: [
    AppComponent,
    FavouriteComponent
  ],
  imports: [
    BrowserModule,HttpClientModule,AuthenticationModule,
    AppRoutingModule,PoemsModule,RouterModule.forRoot(appRoutes),
    BrowserModule,AppRoutingModule,HttpClientModule, 
    AuthenticationModule,ReactiveFormsModule,SnackbarModule, FormsModule
  ],
  providers: [PoemService,AuthenticationService,AuthguardService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }
