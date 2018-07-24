import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HomeComponent} from './components/home/home.component';
import {LoginComponent} from './components/login/login.component';
import {RegisterComponent} from './components/register/register.component';
import {NavBarComponent} from './components/nav-bar/nav-bar.component';
import {LoginService} from "./services/login.service";
import {RegisterService} from "./services/register.service";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {AppRoutingModule} from "./app-routing.module";
import {HttpModule} from "@angular/http";
import {TestService} from "./services/test.service";
import {MatIconModule, MatToolbarModule} from "@angular/material";
import {FlexLayoutModule} from "@angular/flex-layout";
import {ProfileComponent} from './components/profile/profile.component';
import {UserService} from "./services/user.service";
import {CommonModule} from "@angular/common";

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    NavBarComponent,
    ProfileComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    //ReactiveFormsModule,
    HttpModule,
    HttpClientModule,
    MatToolbarModule,
    MatIconModule,
    FlexLayoutModule,
    ReactiveFormsModule,
    CommonModule
  ],
  providers: [
    LoginService,
    RegisterService,
    TestService,
    UserService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
