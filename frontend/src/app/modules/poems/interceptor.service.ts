import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../authentication/authentication.service';

@Injectable()
export class TokenInterceptor {

  constructor(private auth:AuthenticationService) { }

  intercept(request:HttpRequest<any>,next:HttpHandler):Observable<HttpEvent<any>>{
    console.log('in intercept');
    
    
   
 if (request.url.includes('http://localhost')) {
    request=request.clone({
      setHeaders:{
        Authorization: `Bearer ${this.auth.getToken()}`
      }
    });
  }
    return next.handle(request);

}
}
