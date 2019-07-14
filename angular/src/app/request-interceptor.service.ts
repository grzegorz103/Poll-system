import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { HttpHandler, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RequestInterceptorService {

  constructor(public auth: AuthService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      if (this.auth.isAuthenticated()) {
        let token = this.auth.getToken();
          req = req.clone({
              setHeaders: {
                  Authorization: 'Basic ' + token
              }
          });
      }
      return next.handle(req);
  }
}