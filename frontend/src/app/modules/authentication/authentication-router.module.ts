import { Routes, RouterModule } from "@angular/router";
import { RegisterComponent } from "./register/register.component";
import { LoginComponent } from "./login/login.component";
import { NgModel } from "@angular/forms";
import { NgModule } from "@angular/core";


const authRoutes: Routes = [
    {
      path:'',
      children:[
        {
          path: '',
          redirectTo: '/login',
          pathMatch: 'full'
        },
        {
          path: 'register',
          component: RegisterComponent
        },
        {
            path:'login',
            component: LoginComponent
        }
    ]
}
];

@NgModule({
    imports: [RouterModule.forChild(authRoutes)],
    exports: [RouterModule]
  })

export class AuthenticationRoutingModule{
    
}