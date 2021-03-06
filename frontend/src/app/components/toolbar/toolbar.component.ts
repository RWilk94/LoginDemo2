import {Component, OnInit} from '@angular/core';
import {LoginService} from "../../services/login.service";
import {Router} from "@angular/router";
import {NavigationMenuService} from "../../services/navigation-menu.service";

@Component({
  selector: 'app-nav-bar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.css']
})
export class ToolbarComponent implements OnInit {

  constructor(private loginService: LoginService, private router: Router, public navService: NavigationMenuService) {
  }

  ngOnInit() {
  }

  logout() {
    if (this.loginService.checkLogin()) {
      LoginService.logout();
    }
  }

  editProfileOnClick() {
    this.router.navigate(['/profile']);
  }

  changeVisibilityOfSideNav() {
    this.navService.changeVisibilityOfSideNav();
  }

}
