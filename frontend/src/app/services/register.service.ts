import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "../models/user";

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http: HttpClient) { }

  sendUser(user: User) {
    let url = "http://localhost:8080/register";
    let header = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(url, JSON.stringify(user), {headers: header});
  }
}
