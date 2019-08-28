import { Component, OnInit, ElementRef } from '@angular/core';
import { ROUTES } from '../sidebar/sidebar.component';
import { Location, LocationStrategy, PathLocationStrategy } from '@angular/common';
import { AuthService } from 'app/auth.service';
import { Router } from '@angular/router';
import { Poll } from 'app/poll-list/poll-list.component';
import { NotificationService } from 'app/notification.service';
import { DropdownDirective, TOGGLE_STATUS } from 'angular-custom-dropdown';
import { ViewChild } from '@angular/core';

export interface Notification {
    id: number;
    message: string;
    read: boolean;
    createdAt: Date;
    poll: Poll;
}

@Component({
    // moduleId: module.id,
    selector: 'navbar-cmp',
    templateUrl: 'navbar.component.html',
    styleUrls: ['./navbar.component.scss']
})

export class NavbarComponent implements OnInit {
    private listTitles: any[];
    location: Location;
    private toggleButton: any;
    private sidebarVisible: boolean;
    private notifications: Notification[];

    private notificationMap: Map<Notification, number>;
    private reading: boolean;
    @ViewChild('myDropdown') myDropdown: DropdownDirective;
    private dropdownStatus;

    constructor(location: Location, private element: ElementRef,
        public authService: AuthService,
        private router: Router,
        private notificationService: NotificationService) {
        this.location = location;
        this.sidebarVisible = false;
        this.notificationMap = new Map<Notification, number>();
    }
    openNow() {
        this.myDropdown.open();
    }
    ngOnInit() {
        this.listTitles = ROUTES.filter(listTitle => listTitle);
        const navbar: HTMLElement = this.element.nativeElement;
        this.toggleButton = navbar.getElementsByClassName('navbar-toggle')[0];
        this.fetchNotifications();
        this.dropdownStatus = 'Closed';
        this.myDropdown.statusChange()
            .subscribe((status: TOGGLE_STATUS) => {
                if (status === TOGGLE_STATUS.OPEN) {
                    this.dropdownStatus = 'Opened';
                } else if (status === TOGGLE_STATUS.CLOSE) {
                    this.dropdownStatus = 'Closed';
                    this.markNotificationsAsRead();
                }
            });
    }

    fetchNotifications() {
        if (this.authService.isAuthenticated()) {
            this.notificationService.findByUser().subscribe(res => {
                this.notifications = res;
                this.notifications = this.notifications.filter(e => !e.read);

                this.notificationMap = new Map<Notification, number>();
                if (this.notifications.length > 0) {
                    for (let i = 0; i < this.notifications.length; ++i) {
                        const current = this.notifications[i];
                        let found = false;
                        let foundNotif = null;
                        this.notificationMap.forEach((v, k) => {
                            if (k.poll.code === current.poll.code) {
                                foundNotif = k;
                                if (!found) {
                                    found = true;
                                }
                            }
                        })
                        this.notificationMap.set(foundNotif === null ? current : foundNotif,
                            foundNotif === null ? 1 : this.notificationMap.get(foundNotif) + 1);

                    }
                }
            });

        }
        setTimeout(() => this.fetchNotifications(), 1000);
    }

    markNotificationsAsRead() {
        this.notificationService.setNotificationsRead().subscribe(res => res = res);
    }

    logout() {
        this.router.navigate(['/login']);
    }

    sidebarOpen() {
        const toggleButton = this.toggleButton;
        const body = document.getElementsByTagName('body')[0];
        setTimeout(function () {
            toggleButton.classList.add('toggled');
        }, 500);
        body.classList.add('nav-open');

        this.sidebarVisible = true;
    };
    sidebarClose() {
        const body = document.getElementsByTagName('body')[0];
        this.toggleButton.classList.remove('toggled');
        this.sidebarVisible = false;
        body.classList.remove('nav-open');
    };
    sidebarToggle() {
        // const toggleButton = this.toggleButton;
        // const body = document.getElementsByTagName('body')[0];
        if (this.sidebarVisible === false) {
            this.sidebarOpen();
        } else {
            this.sidebarClose();
        }
    };

    getTitle() {
        var titlee = this.location.prepareExternalUrl(this.location.path());
        if (titlee.trim().length > 0) {
            titlee = titlee.split('/').pop();
            for (var item = 0; item < this.listTitles.length; item++) {
                if (this.listTitles[item].path === titlee) {
                    return this.listTitles[item].title;
                }
            }
            titlee = titlee.charAt(0).toUpperCase() + titlee.slice(1);
        }
        return titlee;
    }
}
