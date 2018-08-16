import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class NavigationMenuService {

  public appDrawer: any;
  private opened:boolean = true;

  constructor() {}

  public closeNav() {
    console.log('closeNav');
    this.opened = !this.opened;
    this.appDrawer.close();
  }

  public openNav() {
    console.log('openNav');
    this.opened = !this.opened;
    this.appDrawer.open();
  }

  public changeVisibilityOfSideNav() {
    console.log('changeVisibilityOfSideNav');
    if (this.opened) {
      this.closeNav();
    } else {
      this.openNav();
    }
  }

}
