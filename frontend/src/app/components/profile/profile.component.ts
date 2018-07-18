import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user.service";
import {Cookie} from 'ng2-cookies/ng2-cookies';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(private userService: UserService) {
    this.getUserByUsername();
  }

  ngOnInit() {
  }

  getUserByUsername() {
    this.userService.getUserByUsername(Cookie.get('username')).subscribe(data => console.log(data), error1 => console.log('error'));
  }


}
