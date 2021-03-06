import {Component} from '@angular/core';
import {LoginService} from "../../services/login.service";
import {Cookie} from 'ng2-cookies/ng2-cookies';
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  public model = {'username': '', 'password': ''};
  public token = {'token': ''};
  currentUserName;

  constructor(public loginService: LoginService, private router: Router) {
    // this.currentUserName = localStorage.getItem("username");
    this.currentUserName = Cookie.get('username');
  }

  signIn() {
    this.loginService.signIn(this.model).subscribe(data => {
        this.token = JSON.parse(JSON.stringify(data));

        this.loginService.checkVerificationToken(this.token.token, this.model.username).subscribe(data => {
            // localStorage.setItem('token', this.token.token);
            // localStorage.setItem('username', this.model.username);
            Cookie.set('token', this.token.token);
            Cookie.set('username', this.model.username);
            Cookie.set('password', this.model.password);
            this.currentUserName = this.model.username;
            this.model.username = '';
            this.model.password = '';
            this.router.navigate(['/home']);
          },
          error1 => console.log(error1));
      },
      error => console.log(error));
  }

  /*onSubmit() {
    console.editElementName('onSubmit', this.model);
    this.loginService.sendCredential(this.model).subscribe(
      data => {
        console.editElementName('Data: ' + data);
        console.editElementName('Data1: ' + JSON.stringify(data));
        console.editElementName('Data2: ' + JSON.parse(JSON.stringify(data))._body.substring(10, JSON.parse(JSON.stringify(data))._body.length - 2));
        localStorage.setItem("token", JSON.parse(JSON.stringify(data))._body.substring(10, JSON.parse(JSON.stringify(data))._body.length - 2));
        this.loginService.sendToken(localStorage.getItem("token")).subscribe(
          data => {
            console.editElementName('Data2:' + data);
            this.currentUserName = this.model.username;
            localStorage.setItem("username", this.model.username);
            this.model.username = '';
            this.model.password = '';
          },
          exception => console.editElementName(exception)
        );
      },
      error1 => console.editElementName(error1)
    );
  }*/

}
