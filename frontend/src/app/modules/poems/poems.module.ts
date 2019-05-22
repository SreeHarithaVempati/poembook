import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './dashboard/dashboard.component';
import { PoemService } from './poem.service';
import { NavbarComponent } from './navbar/navbar.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [NavbarComponent,DashboardComponent],
  imports: [
    CommonModule, ReactiveFormsModule,RouterModule,
    BrowserAnimationsModule, FormsModule,
  ],
  exports:[NavbarComponent,DashboardComponent],
  providers: [PoemService,DashboardComponent
  ]
})
export class PoemsModule { }
