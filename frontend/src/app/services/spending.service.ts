import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Cookie} from "ng2-cookies/ng2-cookies";
import {User} from "../models/user";
import {Spending} from "../models/spending";

@Injectable({
  providedIn: 'root'
})
export class SpendingService {

  constructor(private http: HttpClient) { }

  getAllSpending() {
    let url = 'http://localhost:8080/spend';
    let header = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': 'Bearer ' + Cookie.get('token')});
    return this.http.get<Spending[]>(url, {headers: header});
  }

}
