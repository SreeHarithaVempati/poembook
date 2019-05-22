import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './modules/poems/dashboard/dashboard.component';
import { FavouriteComponent } from './modules/poems/favourite/favourite.component';
import { LoginComponent } from './modules/authentication/login/login.component';
import { AuthguardService } from './authguard.service';

const routes: Routes = [

  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate : [AuthguardService]
  },
  {
    path: 'favourites',
    component: FavouriteComponent,
    canActivate : [AuthguardService]
  },
  {
    path:'login',
    component: LoginComponent
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
