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
import {
  MatCardModule, MatDatepickerModule, MatFormFieldModule,
  MatIconModule, MatInputModule,
  MatListModule,
  MatMenuModule, MatNativeDateModule, MatSelectModule,
  MatSidenavModule, MatTableModule, MatTabsModule,
  MatToolbarModule
} from "@angular/material";
import {FlexLayoutModule} from "@angular/flex-layout";
import {ProfileComponent} from './components/profile/profile.component';
import {UserService} from "./services/user.service";
import {CommonModule} from "@angular/common";
import { NavigationMenuComponent } from './components/navigation-menu/navigation-menu.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { MenuListItemComponent } from './components/menu-list-item/menu-list-item.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { SpendingComponent } from './components/spending/spending.component';
import { CategoriesComponent } from './components/categories/categories.component';
import {SpendingService} from "./services/spending.service";
import {NavigationMenuService} from "./services/navigation-menu.service";
import {CategoryService} from "./services/category.service";
import {ModuleService} from "./services/module.service";

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    ToolbarComponent,
    ProfileComponent,
    NavigationMenuComponent,
    MenuListItemComponent,
    DashboardComponent,
    SpendingComponent,
    CategoriesComponent
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
    MatMenuModule,
    MatCardModule,
    MatTabsModule,
    MatInputModule,
    MatFormFieldModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule,
    MatTableModule,

    FlexLayoutModule,
    ReactiveFormsModule,
    CommonModule
  ],
  providers: [
    LoginService,
    RegisterService,
    SpendingService,
    NavigationMenuService,
    CategoryService,
    ModuleService,
    UserService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
