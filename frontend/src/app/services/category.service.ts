import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Category} from "../models/category";
import {Observable} from "rxjs/internal/Observable";
import {User} from "../models/user";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpClient) { }

  getCategories(username: string): Observable<Category[]> {
    let url = 'http://localhost:8080/categories/' + username;
    let header = new HttpHeaders(({'Content-Type': 'application/json'}));
    return this.http.get<Category[]>(url, {headers: header});
  }

  addCategory(category: Category) {
    console.log('addCategory' + category.name);
    console.log(JSON.stringify(category));
    let url = 'http://localhost:8080/categories';
    let header = new HttpHeaders(({'Content-Type': 'application/json'}));
    return this.http.post(url, JSON.stringify(category), {headers: header});
  }
}
