import { Component, OnInit } from '@angular/core';
import { AuthService } from 'app/auth.service';

declare const $: any;
declare interface RouteInfo {
  path: string;
  title: string;
  icon: string;
  class: string;
  authRequired: boolean;
}
export const ROUTES: RouteInfo[] = [
  { path: '/dashboard', title: 'New poll', icon: 'pe-7s-graph', class: '', authRequired: false },
  { path: '/poll', title: 'Explore polls', icon: 'pe-7s-note2', class: '', authRequired: false },
  { path: '/poll/my/all', title: 'My polls', icon: 'pe-7s-user', class: '', authRequired: true },
  // { path: '/typography', title: 'Typography',  icon:'pe-7s-news-paper', class: '' },
  //  { path: '/icons', title: 'Icons',  icon:'pe-7s-science', class: '' },
  //  { path: '/maps', title: 'Maps',  icon:'pe-7s-map-marker', class: '' },
  //   { path: '/notifications', title: 'Notifications',  icon:'pe-7s-bell', class: '' },
];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html'
})
export class SidebarComponent implements OnInit {
  menuItems: any[];

  constructor(public authService: AuthService) { }

  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
  }
  isMobileMenu() {
    if ($(window).width() > 991) {
      return false;
    }
    return true;
  };
}
