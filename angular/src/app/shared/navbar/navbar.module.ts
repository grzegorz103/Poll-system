import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { NavbarComponent } from './navbar.component';
import { AuthService } from 'app/auth.service';
import { NotificationService } from 'app/notification.service';
import { DropdownModule } from 'angular-custom-dropdown';

@NgModule({
    imports: [RouterModule, CommonModule, DropdownModule],
    declarations: [NavbarComponent],
    exports: [NavbarComponent],
    providers: [NotificationService]
})

export class NavbarModule {

}
