import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Http,Headers} from "@angular/http";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  token1: string;

  constructor(private http: HttpClient, private http1: Http) {
  }

  signIn(model) {
    console.log('sign in');
    let tokenUrl = 'http1://localhost:8080/login/';
    let header = new HttpHeaders({'Content-Type': 'application/json'});
    let result = this.http.post(tokenUrl, model, {headers: header});
    console.log('signIn result: ' + result);
    return result;
  }

  sendCredential(model){
    console.log('send credential');
    let tokenUrl = "http1://localhost:8080/login/";
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
  }

  logout() {
    localStorage.setItem("token", "");
    localStorage.setItem("currentUsername", '');
    alert("You just logged out.");
  }

  checkLogin() {
    if (localStorage.getItem("currentUsername") != null && localStorage.getItem("currentUsername") != ''
      && localStorage.getItem("token") != null && localStorage.getItem("token") != '') {
      console.log(localStorage.getItem("currentUsername"));
      console.log(localStorage.getItem("token"));
      return true;
    } else {
      return false;
    }
  }
}
