import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Cookie} from 'ng2-cookies/ng2-cookies';
import {User} from "../models/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

  getUserByUsername(username: string) {
    let model = {'username': username};
    let url = 'http://localhost:8080/user';
    let header = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': 'Bearer ' + Cookie.get('token')});
    return this.http.post<User>(url, model, {headers: header});
    // let url = 'http://localhost:8080/v1/floor1/office1';
    //let header = new HttpHeaders({'Authorization': 'Bearer ' + token});
    //return this.http.get(url, {headers: header});
  }

}
