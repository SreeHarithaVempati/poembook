import { Injectable } from "@angular/core";
import { AuthenticationService } from "./modules/authentication/authentication.service";
import { Router, CanActivate } from "@angular/router";

@Injectable()
export class AuthguardService implements CanActivate{

    constructor(private auth: AuthenticationService,private route:Router){
    }

    canActivate(){
        if(!this.auth.isTokenExpired()){
            console.log('in canActivate');
            return true;
        }
        this.route.navigate(['/login']);
        return false;
    }
}