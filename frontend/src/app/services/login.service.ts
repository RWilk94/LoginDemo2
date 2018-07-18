import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Cookie} from 'ng2-cookies/ng2-cookies';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient /*, private http1: Http*/) {
  }

  signIn(model) {
    console.log('sign in');
    let tokenUrl = 'http://localhost:8080/login';
    let header = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(tokenUrl, model, {headers: header});
  }

  sendVerificationToken(token) {
    console.log('send verification token');
    let url = 'http://localhost:8080/v1/floor1/office1';
    let header = new HttpHeaders({'Authorization': 'Bearer ' + token});
    return this.http.get(url, {headers: header});
  }

  logout() {
    // localStorage.setItem("token", "");
    // localStorage.setItem("currentUsername", '');
    Cookie.delete('token');
    Cookie.delete('username');
    // alert("You just logged out.");
  }

  checkLogin() {
    return Cookie.get('username') != null && Cookie.get('username') != '' && Cookie.get('token') != null && Cookie.get('token') != ''
  }

  /*sendCredential(model){
    console.log('send credential');
    let tokenUrl = "http://localhost:8080/login/";
    let header = new Headers({'Content-Type': 'application/json'});
    let result = this.http1.post(tokenUrl, model, {headers: header});
      //.map(res => res.json());
    console.log(result);
    return result;
  }

  sendToken(token) {
    console.log('setToken');
    let tokenUrl = "http1://localhost:8080/rest/user/users/";
    console.log('Bearer ' + token);
    let getHeaders = new Headers({'Authorization': 'Bearer ' + token});
    return this.http1.get(tokenUrl, {headers: getHeaders})
  }*/

  /*checkLoginOld() {
    if (localStorage.getItem("currentUsername") != null && localStorage.getItem("currentUsername") != ''
      && localStorage.getItem("token") != null && localStorage.getItem("token") != '') {
      console.log(localStorage.getItem("currentUsername"));
      console.log(localStorage.getItem("token"));
      return true;
    } else {
      return false;
    }
  }*/
}
