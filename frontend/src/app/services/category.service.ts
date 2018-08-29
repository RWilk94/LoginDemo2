import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Category} from "../models/category";
import {Observable} from "rxjs/internal/Observable";
import {Cookie} from "ng2-cookies/ng2-cookies";

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

  deleteCategory(category: Category) {
    console.log('deleteCategory' + category.name);
    console.log(JSON.stringify(category));
    let url = 'http://localhost:8080/categories/';
    let header = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': 'Bearer ' + Cookie.get('token')});
    return this.http.delete(url + category.id, {headers: header})
  }

  updateCategory(category: Category) {
    console.log('updateCategory' + category.name);
    console.log(JSON.stringify(category));
    let url = 'http://localhost:8080/categories';
    let header = new HttpHeaders({'Content-Type': 'application/json', 'Authorization': 'Bearer ' + Cookie.get('token')});
    return this.http.patch(url, JSON.stringify(category), {headers: header})
  }
}
