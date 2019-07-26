import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Notification } from './shared/navbar/navbar.component';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

    setNotificationsRead() {
      return this.http.patch<Notification[]>('http://localhost:8080/api/notification/', null);
    }

  constructor(private http: HttpClient) { }

  public findByUser() {
    return this.http.get<Notification[]>('http://localhost:8080/api/notification')
  }
}
