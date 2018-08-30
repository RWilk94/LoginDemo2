import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Cookie} from "ng2-cookies/ng2-cookies";
import {Spending} from "../models/spending";
import {s} from "@angular/core/src/render3";

@Injectable({
  providedIn: 'root'
})
export class SpendingService {

  constructor(private http: HttpClient) { }

  getAllSpending(username: string) {
    let url = 'http://localhost:8080/spending/' + username;
    let header = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': 'Bearer ' + Cookie.get('token')});
    return this.http.get<Spending[]>(url, {headers: header});
  }

  addSpending(spending: Spending) {
    let url = 'http://localhost:8080/spending';
    let header = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': 'Bearer ' + Cookie.get('token')});
    return this.http.put<Spending>(url, JSON.stringify(spending), {headers: header});
  }

  deleteSpending(spending: Spending) {
    let url = 'http://localhost:8080/spending/' + spending.id;
    //console.log(spending.id);
    let header = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': 'Bearer ' + Cookie.get('token')});
    return this.http.delete(url, {headers: header});
  }

  updateSpending(spending: Spending) {
    let url = 'http://localhost:8080/spending';
    let header = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': 'Bearer ' + Cookie.get('token')});
    return this.http.patch(url, JSON.stringify(spending), {headers: header});
  }

}
