import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user.service";
import {Cookie} from 'ng2-cookies/ng2-cookies';
import {User} from "../../models/user";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  currentUser = new User();

  constructor(private userService: UserService) {
    this.getUserByUsername();
  }

  ngOnInit() {
  }

  getUserByUsername() {
    this.userService.getUserByUsername(Cookie.get('username')).subscribe(data => {
      console.log(data);
      this.currentUser = data;
      console.log(this.currentUser);
    },
      error1 => {
        console.log(error1);
        if (error1 instanceof HttpErrorResponse) {
         if (error1.error.message.toString().match('JWT expired')) {
           // TODO session expired - redirect to login page
         }
        }
      }
    );
  }


  onUpdateUser() {
    this.userService.updateUser(this.currentUser).subscribe(data => {
      this.currentUser = data;
    }, error1 => console.log(error1));
  }
}
