import {Component} from '@angular/core';
import {LoginService} from "../../services/login.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  public model = {'username': '', 'password': ''};
  currentUserName;

  constructor(public loginService: LoginService) {
    this.currentUserName = localStorage.getItem("currentUsername");
  }

  signIn() {
    this.loginService.signIn(this.model).subscribe(data => {
        console.log(data);
      },
      error => console.log(error));
  }

  onSubmit() {
    console.log('onSubmit', this.model);
    this.loginService.sendCredential(this.model).subscribe(
      data => {
        console.log('Data: ' + data);
        console.log('Data1: ' + JSON.stringify(data));
        console.log('Data2: ' + JSON.parse(JSON.stringify(data))._body.substring(10, JSON.parse(JSON.stringify(data))._body.length - 2));
        localStorage.setItem("token", JSON.parse(JSON.stringify(data))._body.substring(10, JSON.parse(JSON.stringify(data))._body.length - 2));
        this.loginService.sendToken(localStorage.getItem("token")).subscribe(
          data => {
            console.log('Data2:' + data);
            this.currentUserName = this.model.username;
            localStorage.setItem("currentUsername", this.model.username);
            this.model.username = '';
            this.model.password = '';
          },
          error => console.log(error)
        );
      },
      error1 => console.log(error1)
    );
  }

}
