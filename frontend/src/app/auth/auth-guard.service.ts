import { Injectable } from '@angular/core';
import {CanActivate} from "@angular/router";
import {LoginService} from "../services/login.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate{

  constructor(private loginService: LoginService) { }

  canActivate(): boolean {
    return this.loginService.checkLogin();
  }
}
