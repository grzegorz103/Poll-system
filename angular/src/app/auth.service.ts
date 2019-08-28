import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  url: string;

  constructor(
    private http: HttpClient
  ) {
    this.url = 'https://hot-poll.herokuapp.com/api/user/admin';
  }

  public getToken(): string {
    return sessionStorage.getItem('token');
  }

  public isAuthenticated() {
    return this.getToken() !== null && this.getToken() !== '';
  }

  public hasAdminRole() {
    return localStorage.getItem('adminRole') !== null && sessionStorage.getItem('adminRole') === 'true';
  }

  public fetchAdminRole() {
    this.http.get<boolean>(this.url).subscribe(res => sessionStorage.setItem('adminRole', String(res)));
  }

  public getUsername(){
    return sessionStorage.getItem('username');
  }

}
