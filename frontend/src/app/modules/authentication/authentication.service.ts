import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
export const TOKEN_NAME:string='jwt_token';
import * as jwt_decode from 'jwt-decode';


@Injectable()
export class AuthenticationService{
    springEndPoint: string;
    token:string;

    constructor(private httpClient: HttpClient){
        this.springEndPoint="http://localhost:5656/api/v1/userlistservice";       
    }

    registerUser(newUser){
        const url=this.springEndPoint+"/register";
        console.log(newUser);
        return this.httpClient.post(url,newUser, {responseType: 'text'});
    }

    loginUser(newUser){
        const url=this.springEndPoint+"/login";
        console.log('newUser', newUser);
        return this.httpClient.post(url,newUser);
    }
    
    setToken(token:string){
        return localStorage.setItem(TOKEN_NAME,token);
    }

    getToken(){
        return localStorage.getItem(TOKEN_NAME);
    }

    deleteToken(){
        return localStorage.removeItem(TOKEN_NAME);
    }

    getTokenExpirationDate(token: string): Date{
        const decoded=jwt_decode(token);
        if (decoded.exp === undefined) return null;
        const date=new Date(0);
        date.setUTCSeconds(decoded.exp);
        return date;
    }

    isTokenExpired(token?: string): boolean{
        if(!token) token=this.getToken();
        if(!token) return true;
        const date =this.getTokenExpirationDate(token);
        if (date===undefined || date===null) return false;
        return !(date.valueOf() > new Date().valueOf());
    }
    }