import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';

import { AppRoutingModule } from './app.routing';
import { NavbarModule } from './shared/navbar/navbar.module';
import { FooterModule } from './shared/footer/footer.module';
import { SidebarModule } from './sidebar/sidebar.module';

import { AppComponent } from './app.component';

import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { PollListComponent } from './poll-list/poll-list.component';
import { PollService } from './poll-list/poll.service';
import { HttpClientModule } from '@angular/common/http';
import { PollDetailsComponent } from './poll-details/poll-details.component';
import { MomentModule } from 'angular2-moment';
import { UserRegistrationComponent } from './user-registration/user-registration.component';
import { UserService } from './user.service';



@NgModule({
  imports: [
    BrowserAnimationsModule,
    FormsModule,
    RouterModule,
    HttpModule,
    NavbarModule,
    FooterModule,
    SidebarModule,
    AppRoutingModule,
    HttpClientModule
  ],
  declarations: [
    AppComponent,
    AdminLayoutComponent,
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
