import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Notification } from './shared/navbar/navbar.component';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

    setNotificationsRead() {
      return this.http.patch<Notification[]>('https://hot-poll.herokuapp.com/api/notification/', null);
    }

  constructor(private http: HttpClient) { }

  public findByUser() {
    return this.http.get<Notification[]>('https://hot-poll.herokuapp.com/api/notification')
  }
}
