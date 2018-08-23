import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/internal/Observable";
import {Module} from "../models/module";

@Injectable({
  providedIn: 'root'
})
export class ModuleService {

  constructor(private http: HttpClient) { }

  getModules(): Observable<Module[]> {
    let url = 'http://localhost:8080/modules';
    let header = new HttpHeaders(({'Content-Type': 'application/json'}));
    return this.http.get<Module[]>(url, {headers: header});
  }
}
