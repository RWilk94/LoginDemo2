import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HomeComponent} from './components/home/home.component';
import {LoginComponent} from './components/login/login.component';
import {RegisterComponent} from './components/register/register.component';
import {ToolbarComponent} from './components/toolbar/toolbar.component';
import {LoginService} from "./services/login.service";
import {RegisterService} from "./services/register.service";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {AppRoutingModule} from "./app-routing.module";
import {MatIconModule, MatListModule, MatSidenavModule, MatToolbarModule} from "@angular/material";
import {FlexLayoutModule} from "@angular/flex-layout";
import {ProfileComponent} from './components/profile/profile.component';
import {UserService} from "./services/user.service";
import {CommonModule} from "@angular/common";
import { NavigationMenuComponent } from './components/navigation-menu/navigation-menu.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { MenuListItemComponent } from './components/menu-list-item/menu-list-item.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    ToolbarComponent,
    ProfileComponent,
    NavigationMenuComponent,
    MenuListItemComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    HttpClientModule,

    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatSidenavModule,
    MatListModule,

    FlexLayoutModule,
    ReactiveFormsModule,
    CommonModule
  ],
  providers: [
    LoginService,
    RegisterService,
    UserService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
