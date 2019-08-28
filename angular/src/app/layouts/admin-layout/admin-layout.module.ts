import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { LbdModule } from '../../lbd/lbd.module';
import { NguiMapModule} from '@ngui/map';

import { AdminLayoutRoutes } from './admin-layout.routing';

import { HomeComponent } from '../../home/home.component';
import { UserComponent } from '../../user/user.component';
import { TablesComponent } from '../../tables/tables.component';
import { NotificationsComponent } from '../../notifications/notifications.component';
import { UpgradeComponent } from '../../upgrade/upgrade.component';
import { PollListComponent } from 'app/poll-list/poll-list.component';
import { PollService } from 'app/poll-list/poll.service';
import { PollDetailsComponent } from 'app/poll-details/poll-details.component';
import { MomentModule } from 'angular2-moment';
import { NavbarComponent } from 'app/shared/navbar/navbar.component';
import { UserRegistrationComponent } from 'app/user-registration/user-registration.component';
import { LoginComponent } from 'app/login/login.component';
import { PollUsersComponent } from 'app/poll-users/poll-users.component';
import { AuthService } from 'app/auth.service';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
    FormsModule,
    LbdModule,
    MomentModule, 
  ],
  declarations: [
    HomeComponent,
    UserComponent,
    TablesComponent,
    NotificationsComponent,
    UpgradeComponent,
    PollListComponent, 
    PollDetailsComponent,
    UserRegistrationComponent,
    LoginComponent,
    PollUsersComponent
  ],
  providers: [PollService],
})

export class AdminLayoutModule {}
