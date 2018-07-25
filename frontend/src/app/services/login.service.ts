import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Cookie} from 'ng2-cookies/ng2-cookies';
import {User} from "../models/user";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) {
  }

  signIn(model) {
    console.log('sign in');
    let tokenUrl = 'http://localhost:8080/login';
    let header = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(tokenUrl, model, {headers: header});
  }

  checkVerificationToken(token, username) {
    console.log('send verification token');
    let model = {'username': username};
    model.username = username;
    let url = 'http://localhost:8080/user';
    let header = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': 'Bearer ' + token});
    return this.http.post<User>(url, model, {headers: header});
  }

  static logout() {
    Cookie.delete('token');
    Cookie.delete('username');
  }

  checkLogin() {
    return Cookie.get('username') != null && Cookie.get('username') != '' && Cookie.get('token') != null && Cookie.get('token') != ''
  }

  getUsername() {
    console.log(Cookie.get('username'));
    return Cookie.get('username');
  }
}
