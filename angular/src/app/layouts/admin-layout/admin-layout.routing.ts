import { Routes } from '@angular/router';

import { HomeComponent } from '../../home/home.component';
import { UserComponent } from '../../user/user.component';
import { TablesComponent } from '../../tables/tables.component';
import { NotificationsComponent } from '../../notifications/notifications.component';
import { UpgradeComponent } from '../../upgrade/upgrade.component';
import { PollListComponent } from 'app/poll-list/poll-list.component';
import { PollDetailsComponent } from 'app/poll-details/poll-details.component';
import { UserRegistrationComponent } from 'app/user-registration/user-registration.component';
import { LoginComponent } from 'app/login/login.component';
import { PollUsersComponent } from 'app/poll-users/poll-users.component';

export const AdminLayoutRoutes: Routes = [
    { path: 'dashboard', component: HomeComponent },
    { path: 'user', component: UserComponent },
    { path: 'table', component: TablesComponent },
    { path: 'notifications', component: NotificationsComponent },
    { path: 'upgrade', component: UpgradeComponent },
    { path: 'poll', component: PollListComponent },
    { path: 'poll/:id', component: PollDetailsComponent },
    { path: 'signup', component: UserRegistrationComponent },
    { path: 'login', component: LoginComponent },
    { path: 'poll/my/all', component: PollUsersComponent }
];
