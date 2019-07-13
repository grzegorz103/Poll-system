import { Component, OnInit } from '@angular/core';
import { UserService } from 'app/user.service';


export class User {
  id: number;
  username: string;
  password: string;
  confirmPassword: string;
  email: string;
}

@Component({
  selector: 'app-user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.scss']
})
export class UserRegistrationComponent implements OnInit {

user: User;

  constructor(
    private userService: UserService
  ) { 
    this.user = new User();
  }

  ngOnInit() {
  }

  onSubmit() {
    this.userService.save(this.user).subscribe(result => alert('Thank you for registering'));
    this.user = new User();
  }

}
