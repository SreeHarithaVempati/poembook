import { NgModule } from '@angular/core';
import { RouterModule,Routes } from '@angular/router';
import { LoginComponent } from 'src/app/modules/authentication/login/login.component';
import { RegisterComponent } from 'src/app/modules/authentication/register/register.component'
import { CommonModule } from '@angular/common';
import { AuthenticationRoutingModule } from './authentication-router.module';
import { AuthenticationService } from './authentication.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    LoginComponent, RegisterComponent
  ],

  imports: [
      CommonModule,FormsModule,AuthenticationRoutingModule,RouterModule,
      ReactiveFormsModule
  ],
  providers: [AuthenticationService],
  exports: [RegisterComponent,LoginComponent]
  
})
export class AuthenticationModule { }
