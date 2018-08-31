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
  MAT_DATE_LOCALE, MatButtonModule,
  MatCardModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatFormFieldModule,
  MatIconModule, MatInputModule,
  MatListModule,
  MatMenuModule, MatNativeDateModule, MatSelectModule,
  MatSidenavModule, MatSortModule, MatTableModule, MatTabsModule,
  MatToolbarModule, MatTooltipModule
} from "@angular/material";
import {FlexLayoutModule} from "@angular/flex-layout";
import {ProfileComponent} from './components/profile/profile.component';
import {UserService} from "./services/user.service";
import {CommonModule} from "@angular/common";
import {NavigationMenuComponent} from './components/navigation-menu/navigation-menu.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MenuListItemComponent} from './components/menu-list-item/menu-list-item.component';
import {DashboardComponent} from './components/dashboard/dashboard.component';
import {SpendingComponent} from './components/spending/spending.component';
import {CategoriesComponent} from './components/categories/categories.component';
import {SpendingService} from "./services/spending.service";
import {NavigationMenuService} from "./services/navigation-menu.service";
import {CategoryService} from "./services/category.service";
import {ModuleService} from "./services/module.service";
import {ToasterModule} from "angular2-toaster";
import {DialogConfirmDeleteComponent} from './components/dialog-confirm-delete/dialog-confirm-delete.component';

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
    CategoriesComponent,
    DialogConfirmDeleteComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    HttpClientModule,
    ToasterModule.forRoot(),
    //ToastyModule.forRoot(),
    //AdvGrowlModule,
    // NotifierModule,
    //ToastModule.forRoot(),

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
    MatSortModule,
    MatTooltipModule,
    MatExpansionModule,
    MatDialogModule,
    // MatButtonModule,

    FlexLayoutModule,
    ReactiveFormsModule,
    CommonModule
  ],
  entryComponents: [DialogConfirmDeleteComponent],
  providers: [
    LoginService,
    RegisterService,
    SpendingService,
    NavigationMenuService,
    CategoryService,
    ModuleService,
    UserService,
    {provide: MAT_DATE_LOCALE, useValue: 'pl-PL'}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
