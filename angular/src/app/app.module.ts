import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';

import { AppRoutingModule } from './app.routing';
import { NavbarModule } from './shared/navbar/navbar.module';
import { FooterModule } from './shared/footer/footer.module';
import { SidebarModule } from './shared/sidebar/sidebar.module';

import { AppComponent } from './app.component';

import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { PollListComponent } from './poll-list/poll-list.component';
import { PollService } from './poll-list/poll.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { PollDetailsComponent } from './poll-details/poll-details.component';
import { MomentModule } from 'angular2-moment';
import { UserRegistrationComponent } from './user-registration/user-registration.component';
import { UserService } from './user.service';
import { LoginComponent } from './login/login.component';
import { AuthService } from './auth.service';
import { RequestInterceptorService } from './request-interceptor.service';
import { PollUsersComponent } from './poll-users/poll-users.component';


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
  providers: [UserService, AuthService, {
    provide: HTTP_INTERCEPTORS,
    useClass: RequestInterceptorService,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
